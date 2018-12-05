import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InventoryItem } from './models/inventory-item';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class InventoryItemService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authService.getToken()}`
    })
  };

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) { }

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/itemslist';

  index() {
    console.log('URL: ' + this.url);
    // return this.http.get<InventoryItem[]>(this.url, this.httpOptions).pipe(
      return this.http.get<InventoryItem[]>(this.url).pipe(
        catchError((err: any) => {
        console.log('error in inventoryItemService index():');
        console.log(err);
      return throwError('Error getting InventoryItem List');
      })
    );
  }


  loadVendorItems() {
    console.log(this.baseUrl);
    return this.http.get<InventoryItem[]>(this.baseUrl + 'api/item/vendor', this.httpOptions).pipe(
      catchError((err: any) => {
        console.log('error in inventoryItemService loadVendorItems():');
        console.log(err);
      return throwError('Error getting InventoryItem List for Vendor');
      })
    );
  }

  addVendorItems(vendorItem: InventoryItem) {
    return this.http.post<InventoryItem>(this.baseUrl + 'api/item', vendorItem, this.httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating VendorInventoryItem');
        })
    );
  }

  updateVendorItems(vendorItem: InventoryItem) {
    return this.http.put<InventoryItem>(this.baseUrl + 'api/item/' + vendorItem.id, vendorItem, this.httpOptions)
    .pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('Error updating VendorInventoryItem');
      })
    );

  }
}
