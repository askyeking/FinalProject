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
}
