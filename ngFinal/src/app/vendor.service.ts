import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Vendor } from './models/vendor';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VendorService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/vendor';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authService.getToken()}`
    })
  };

  constructor(private http: HttpClient, private authService: AuthService) { }


  index() {
    return this.http.get<Vendor[]>(this.url, this.httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
      return throwError('Error getting vendors');
      })
    );

  }
}
