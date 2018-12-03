import { Injectable } from '@angular/core';
import { User } from './models/user';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Customer } from './models/customer';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.baseUrl;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authService.getToken()}`
    })
  };
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}

  createProfiles(user: User) {
    console.log('user at service');
    console.log(user);

    return this.http
      .post<User>(this.baseUrl + 'api/user/register', user, this.httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('todoService.create(): Error adding new Todo');
        })
      );
  }
}
