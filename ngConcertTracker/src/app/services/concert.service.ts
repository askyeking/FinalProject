import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Concert } from '../models/concert';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ConcertService {

  private url = environment.baseUrl + "api/concerts";

  constructor(private http: HttpClient, private authService: AuthService) { }



  getHttpOptions() {


    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  getUpcomingConcerts() : Observable<Concert[]> {

    return this.http.get<Concert[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ConcertService.getConcerts(): error retrieving concerts: ' + err)
        );
      })
    );

  }


  createConcert(concert: Concert) : Observable<Concert> {
    console.log(concert);
    return this.http.post<Concert>(this.url, concert, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ConcertService.getConcerts(): error retrieving concerts: ' + err)
        );
      })
    );
  }
}
