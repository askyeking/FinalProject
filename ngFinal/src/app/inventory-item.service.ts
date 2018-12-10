import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, Optional } from '@angular/core';
import { Router } from '@angular/router';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { InventoryItem } from './models/inventory-item';
import { User } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class InventoryItemService {
  // httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     Authorization: `Basic ${this.authService.getToken()}`
  //   })
  // };

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) { }

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/itemslist';

  index() {
      return this.http.get<InventoryItem[]>(this.url).pipe(
        catchError((err: any) => {
        console.log('error in inventoryItemService index():');
        console.log(err);
      return throwError('Error getting InventoryItem List');
      })
    );
  }


  loadVendorItems() {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http.get<InventoryItem[]>(this.baseUrl + 'api/item/vendor', httpOptions).pipe(
      catchError((err: any) => {
        console.log('error in inventoryItemService loadVendorItems():');
        console.log(err);
      return throwError('Error getting InventoryItem List for Vendor');
      })
    );
  }

  addVendorItems(vendorItem: InventoryItem) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http.post<InventoryItem>(this.baseUrl + 'api/item', vendorItem, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating VendorInventoryItem');
        })
    );
  }

  updateVendorItems(vendorItem: InventoryItem) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http.put<InventoryItem>(this.baseUrl + 'api/item/' + vendorItem.id, vendorItem, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('Error updating VendorInventoryItem');
      })
    );

  }

  getOne(id: number): Observable<InventoryItem> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${this.authService.getToken()}`
      })
    };

    return this.http.get<InventoryItem>(this.baseUrl + 'api/item/' + id, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('userService.retrieveProfiles(): Error creating profiles');
      })
    );
  }

  // getPostingUser(userId: number) {
  //   return this.http.get<User>(this.baseUrl + 'api/item/user/' + userId, this.httpOptions)
  //   .pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError('userService.retrieveProfiles(): Error creating profiles');
  //     })
  //   );
  // }



}
