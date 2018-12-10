import { CommentFromCustomer } from './models/comment-from-customer';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentFromCustomerService {
  private baseUrl = environment.baseUrl;


  postComment(customerComment: CommentFromCustomer) {

    console.log("inside postComment");

    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };


    return this.http
      .post<CommentFromCustomer>(
        this.baseUrl +
          "api/customercomment/itemRental/" +
          customerComment.itemRental.id,
        customerComment,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            "Error: CommentFromVendorService.postComment()"
          );
        })
      );
  }

  // httpOptions = {

  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     Authorization: `Basic ${this.authService.getToken()}`
  //   })
  // };

  constructor(private http: HttpClient, private authService: AuthService) { }

}
