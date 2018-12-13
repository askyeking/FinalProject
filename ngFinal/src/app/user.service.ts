import { Vendor } from './models/vendor';
import { Injectable } from '@angular/core';
import { User } from './models/user';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';
import { Customer } from './models/customer';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.baseUrl;

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}


  // this method is used to persis a user object to the database
  createProfiles(user: User) {
    console.log(user);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };
    console.log(httpOptions);

    return this.http
      .post<User>(this.baseUrl + 'api/user/register', user, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('userService.create(): Error creating profiles');
        })
      );
  }
  // This method retrieves a user to access it's vendor and customer data and functionality
  retrieveProfiles(): Observable<User> {
    console.log('At userService.retrieveProfiles()');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };

    return this.http.get<User>(this.baseUrl + 'api/user/email', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('userService.retrieveProfiles(): Error creating profiles');
      })
    );
  }

  // this method is used for persisting updates to a user
  update(user: User): Observable<User> {
    console.log(user);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };

    return this.http.patch<User>(this.baseUrl + 'api/user/customer', user, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('userService.update(): Error updateing customer');
      })
    );
  }

  // this method is used to persist changes specifically for a vendor
  updateVendor(user: User): Observable<User> {
    console.log('In Service.updateVendor()');
    console.log(user);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http.patch<User>(this.baseUrl + 'api/vendor', user.vendor, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('userService.updateVendor(): Error updating vendor');
      })
    );
  }


  // method for creating a new vendor and persisting it to the database
  createVendor(vendor: Vendor) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };

    return this.http.post<User>(this.baseUrl + 'api/user/register/vendor', vendor, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('userService.updateVendor(): Error creating vendor');
      })
    );
  }



}
