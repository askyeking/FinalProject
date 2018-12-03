import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, catchError } from 'rxjs/operators';
import { observable, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) {
  }

  login(email, password) {
    console.log('auth login email & password');
    console.log(email);
    console.log(password);


    // Make token
    const token = this.generateBasicAuthToken(email, password);
    // Send token as Authorization header (this is spring security convention for basic auth)
    const headers = new HttpHeaders()
      .set('Authorization', `Basic ${token}`);
      // .set('X-Request-With', 'XMLHttpRequest');

    // create request to authenticate credentials
    return this.http
      .get('http://localhost:8090/authenticate', {headers})
      .pipe(
        tap((res) => {
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



    register(user) {
    return this.http.post('http://localhost:8090/register', user)
    .pipe(
        tap((res) => {  // create a user and then upon success, log them in
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  logout() {
    localStorage.removeItem('token');
  }

  checkLogin() {
    if (localStorage.getItem('token')) {
      return true;
    }
    return false;
  }

  generateBasicAuthToken(username, password) {
    return btoa(`${username}:${password}`);
  }

  getToken() {
    return localStorage.getItem('token');
  }

}
