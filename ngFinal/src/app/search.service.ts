import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { InventoryItem } from './models/inventory-item';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Vendor } from './models/vendor';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authService.getToken()}`
    })
  };


  searchParameter: string;


  search(parameter: string,  keyword: string) {
  return this.http.get<InventoryItem[]>(this.url + "/items/" + parameter + "/" + keyword, this.httpOptions).pipe(
    catchError((err: any) => {
      console.log(err);
    return throwError('Error getting items with parameter: ' + parameter + "and keyword: " + keyword);
    })
  );

  }


  searchVendors(keyword: string) {
    return this.http.get<Vendor[]>(this.url + "/vendor/" + keyword, this.httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
      return throwError('Error getting vendors');
      })
    );
  }

  constructor(private http: HttpClient, private authService: AuthService) { }
}
