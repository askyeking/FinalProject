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

  createProfiles(user: User) {
    console.log(user);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
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

  retrieveProfiles(): Observable<User> {
    console.log('At userService.retrieveProfiles()');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
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

  update(user: User): Observable<User> {
    console.log(user);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
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

}
