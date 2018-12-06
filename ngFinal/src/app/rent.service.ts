import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { ItemRental } from './models/item-rental';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RentService {
  private baseUrl = environment.baseUrl;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authService.getToken()}`
    })
  };
  constructor(private http: HttpClient, private authService: AuthService, private router: Router) { }

  rentItem(itemToRent: ItemRental) {
    console.log("rentService.rentItem()");
    return this.http.post<ItemRental>(this.baseUrl + 'api/rental', itemToRent, this.httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating VendorInventoryItem');
        })
    );
  }

  returnItem(itemToReturn: ItemRental) {

  }


}
