import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Vendor } from './models/vendor';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { User } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class VendorService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/vendor';
  // httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     Authorization: `Basic ${this.authService.getToken()}`
  //   })
  // };


  constructor(private http: HttpClient, private authService: AuthService) { }


  getUserByVendorId(vendorId: number) {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http.get<User>(this.url + '/user/' + vendorId, httpOptions).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError('Error retrieving a user: vendorService.getUserByVendorId()');
      })
    );
  }
  getVendorByInventoryItemId(itemId: number) {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http.get<User>(this.url + '/profile/' + itemId, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
      return throwError('Error retrieving a vendor: vendorService.getVendorByInventoryItemId()');
      })
    );
  }

  index() {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http.get<Vendor[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
      return throwError('Error getting vendors');
      })
    );

  }
}
