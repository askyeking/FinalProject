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
  // fields
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/vendor';


  // methods
  constructor(private http: HttpClient, private authService: AuthService) { }

  // This method gets a user from the database based on the id provided
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

  // This method gets a vendor based on an items's id
  getVendorByInventoryItemId(itemId: number) {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };

    return this.http.get<User>(this.url + '/profile/' + itemId, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
      return throwError('Error retrieving a vendor: vendorService.getVendorByInventoryItemId()');
      })
    );
  }
  // index method for getting a list of all vendors
  index() {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
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
