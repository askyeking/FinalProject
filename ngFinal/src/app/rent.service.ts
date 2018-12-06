import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from 'selenium-webdriver/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

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

  rentItem() {

  }

  returnItem() {

  }


}
