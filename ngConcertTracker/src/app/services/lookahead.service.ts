import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { VenueProjection } from '../projections/venue-projection';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { BandProjection } from '../projections/band-projection';

@Injectable({
  providedIn: 'root'
})
export class LookaheadService {

  private url = environment.baseUrl + "api/lookahead";

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



  venueLookAhead(name: string): Observable<VenueProjection[]>{
    return this.http.get<VenueProjection[]>(this.url + "/venues/" + name, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('LookAheadService.venueLookAhead(): error retrieving venues: ' + err)
        );
      })
    );

  }

  bandLookAhead(name: string): Observable<BandProjection[]>{
    return this.http.get<BandProjection[]>(this.url + "/bands/" + name, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('LookAheadService.bandLookAhead(): error retrieving bands: ' + err)
        );
      })
    );

  }


}
