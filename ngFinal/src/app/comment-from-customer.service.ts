import { CommentFromCustomer } from "./models/comment-from-customer";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class CommentFromCustomerService {
  private baseUrl = environment.baseUrl;
  // method for persisting a new customer comment to the database
  postComment(customerComment: CommentFromCustomer) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
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
          return throwError("Error: CommentFromVendorService.postComment()");
        })
      );
  }
// method for setting a comment to inactive in the database
  deleteComment(commentId: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };

    return this.http
      .delete<String>(
        this.baseUrl + "api/customercomment/" + commentId,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("Error: CommentFromVendorService.postComment()");
        })
      );
  }
// method for persisting changes to a comment in the database
  updateComment(comment: CommentFromCustomer) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http
      .put<string>(this.baseUrl + "api/customercomment", comment, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("Error: CommentFromVendorService.postComment()");
        })
      );
  }

  // httpOptions = {

  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     Authorization: `Basic ${this.authService.getToken()}`,
  //      "x-requested-with": "XMLHttpRequest"
  //   })
  // };

  constructor(private http: HttpClient, private authService: AuthService) {}
}
