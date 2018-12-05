import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { RegisterComponent } from './register/register.component';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private router: Router) {
  }

  login(email, password) {
    console.log('auth login email & password');
    console.log(email);
    console.log(password);
    // this.regComp.refresh();


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

  logout() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('landing');
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
