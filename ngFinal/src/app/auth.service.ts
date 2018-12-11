import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private router: Router) {
  }
  // this method is used to log a user in
  login(email, password) {
    // Make token
    const token = this.generateBasicAuthToken(email, password);
    // Send token as Authorization header (this is spring security convention for basic auth)
    const headers = new HttpHeaders()
      .set('Authorization', `Basic ${token}`);
      // .set('X-Request-With', 'XMLHttpRequest');

    // create request to authenticate credentials
    console.log('Base URL: ');
    console.log(this.baseUrl);
    return this.http
      .get(this.baseUrl + 'authenticate', {headers})
      .pipe(
        tap((res) => {
          console.log('hey, inside tap');
          localStorage.setItem('token' , token);
          console.log(res);
          return res;
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM - auth login');
        })
      );
  }


// method for submitting a new user account
    register(user) {
      console.log('AuthService.register(user)');
      console.log(user);


    return this.http.post(this.baseUrl + 'register', user)
    .pipe(
        tap((res) => {  // create a user and then upon success, log them in
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }
// method to logout a user, by removing it's token from localstorage
  logout() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('landing');
  }
// check if the user is logged in
  checkLogin() {
    if (localStorage.getItem('token')) {
      return true;
    }
    return false;
  }
// used to generate the token stored in localstorage for identifying the logged in user
  generateBasicAuthToken(username, password) {
    return btoa(`${username}:${password}`);
  }
// used to get the token out of localstorage for authentication purposes
  getToken() {
    return localStorage.getItem('token');
  }

}
