import { CommentFromVendor } from "./models/comment-from-vendor";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { ItemRental } from "./models/item-rental";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class CommentFromVendorService {
  private baseUrl = environment.baseUrl;

  // httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     Authorization: `Basic ${this.authService.getToken()}`
  //   })
  // };

  constructor(private http: HttpClient, private authService: AuthService) {}
  // this method is used for persisting a new vendorComment to the database
  postComment(vendorComment: CommentFromVendor) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };

    return this.http
      .post<CommentFromVendor>(
        this.baseUrl +
          "api/vendorcomment/itemRental/" +
          vendorComment.itemRental.id,
        vendorComment,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("Error: CommentFromVendorService.postComment()");
        })
      );
  }
  // used for setting a comment to inactive
  deleteComment(commentId) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Basic ${this.authService.getToken()}`,
        "x-requested-with": "XMLHttpRequest"
      })
    };

    return this.http
      .delete<number>(
        this.baseUrl + "api/vendorcomment/" + commentId,
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("Error: CommentFromVendorService.postComment()");
        })
      );
  }
}
