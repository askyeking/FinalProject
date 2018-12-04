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

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}

  createProfiles(user: User) {
    console.log('user at UserService');
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

  // register(user: User) {
  //   this.authService.register(user).subscribe(
  //     data => {
  //       console.log('component register email & password');
  //       console.log(user.email);
  //       console.log(user.password);

  //       this.authService.login(user.email, user.password).subscribe(
  //         // tslint:disable-next-line:no-shadowed-variable
  //         data => {
  //           console.log('we think login worked');
  //           return true;
  //         },
  //         err => {
  //           console.log('we think login didnt work');
  //           return false;
  //         }
  //       );
  //     },
  //     err => {
  //       console.error('Observer got an error' + err);
  //       return false;
  //     }
  //   );
  // }
}
