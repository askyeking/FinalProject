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

  // httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     Authorization: `Basic ${this.authService.getToken()}`
  //   })
  // };
  constructor(private http: HttpClient, private authService: AuthService, private router: Router) { }

  retrieveCustomersRentals(customerId: number) {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http.get<ItemRental[]>(this.baseUrl + 'api/rental/customer/' + customerId, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error rentService.retrieveCustomersRentals()');
        })
    );
  }

  rentItem(itemToRent: ItemRental) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };
    return this.http.post<ItemRental>(this.baseUrl + 'api/rental', itemToRent, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating VendorInventoryItem');
        })
    );
  }

  getItem(id: number) {const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authService.getToken()}`
    })
  };
    return this.http.get<ItemRental>(this.baseUrl + 'api/rental/' + id, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error getting RentalItem (rentService)');
        })
    );
  }

  returnItem(itemToReturn: ItemRental) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };
    return this.http.patch<ItemRental>(this.baseUrl + 'api/rental', itemToReturn, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating VendorInventoryItem');
        })
    );
  }

  retrieveItemRentalHistory(itemId: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };
    return this.http.get<ItemRental[]>(this.baseUrl + 'api/rental/item/' + itemId, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error rentService.retrieveItemRentalHistory()');
        })
    );
  }


}
