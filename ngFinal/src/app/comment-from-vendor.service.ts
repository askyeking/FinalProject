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



  postComment(vendorComment: CommentFromVendor) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Basic ${this.authService.getToken()}`
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
          return throwError(
            "Error: CommentFromVendorService.postComment()"
          );
        })
      );
  }
}
