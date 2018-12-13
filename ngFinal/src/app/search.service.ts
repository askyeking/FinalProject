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
 searchParameter: string;

  // this method is used for submitting a search from the navbar
  // specifically for items including the search by name and the search by category
  search(parameter: string,  keyword: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };
    console.log(httpOptions);

  return this.http.get<InventoryItem[]>(this.url + "/items/" + parameter + "/" + keyword, httpOptions).pipe(
    catchError((err: any) => {
      console.log(err);
    return throwError('Error getting items with parameter: ' + parameter + "and keyword: " + keyword);
    })
  );

  }


  // this method is used for searching for vendors from the navbar
  searchVendors(keyword: string) {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };


    return this.http.get<Vendor[]>(this.url + "/vendor/search/" + keyword, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
      return throwError('Error getting vendors');
      })
    );
  }

  constructor(private http: HttpClient, private authService: AuthService) { }
}
