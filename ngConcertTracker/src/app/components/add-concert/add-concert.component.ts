import { LookaheadService } from './../../services/lookahead.service';
import { Component, inject, OnInit, TemplateRef } from '@angular/core';
import { ModalDismissReasons, NgbAlertModule, NgbCalendar, NgbDate, NgbDatepickerModule, NgbDateStruct, NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ConcertService } from '../../services/concert.service';
import { loadTranslations } from '@angular/localize';
import { FormsModule } from '@angular/forms';
import { Concert } from '../../models/concert';
import { Band } from '../../models/band';
import { OperatorFunction, Observable, debounceTime, distinctUntilChanged, map, catchError, of, switchMap, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ConcertAct } from '../../models/concert-act';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-concert',
  standalone: true,
  imports: [NgbModule, FormsModule, CommonModule, NgbDatepickerModule, NgbAlertModule],
  templateUrl: './add-concert.component.html',
  styleUrl: './add-concert.component.css'
})
export class AddConcertComponent implements OnInit{

  venueSearch = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(300), // wait for 300ms after the last event before emitting last event
      distinctUntilChanged(), // ignore if next search term is same as previous
      switchMap(term =>
        this.lookService.venueLookAhead(term) // switch to new search observable each time
      )
    );

    bandSearch = (text$: Observable<string>) =>
      text$.pipe(
        debounceTime(300), // wait for 300ms after the last event before emitting last event
        distinctUntilChanged(), // ignore if next search term is same as previous
        switchMap(term =>
          this.lookService.bandLookAhead(term) // switch to new search observable each time
        )
      );

  formatter = (result: { name: string }) => result.name;
  inputFormatter = this.formatter;
  resultFormatter = this.formatter;

  concertDate: NgbDate = new NgbDate(0,0,0);
  newConcert: Concert;
  headliner: any = {};
  bands: Band[] = [];
  venueName: string = "";
  numOfActs: number = 0;
	doorsTime = { hour: 7, minute: 0 };
  startTime = { hour: 8, minute: 0 };
  dayOfEvent = {};



  constructor(
    private concertService: ConcertService,
    private modalService: NgbModal,
    private lookService: LookaheadService,
    private http: HttpClient,
    private router: Router
  ) {
    this.newConcert = new Concert();
    this.newConcert.ageRequirement = "All Ages";
  }


  ngOnInit(): void {

  }

  updateNumOfBands() : void {
    if(this.bands.length < this.numOfActs) {
      while(this.bands.length < this.numOfActs) {
        this.bands.push(new Band());
      }
    } else {
      this.bands = this.bands.slice(0,this.numOfActs);
    }
  }

  saveConcert(concert: Concert) : void {
    this.generateConcertActs();
    this.newConcert.doorsTime = this.stringifyTime(this.doorsTime);
    this.newConcert.startTime = this.stringifyTime(this.startTime);
    this.newConcert.dayOfEvent = this.stringifyDate(this.dayOfEvent);
    this.newConcert.venue.name = this.newConcert.venue.name;
    this.concertService.createConcert(concert).subscribe({
      next: (concert) => {
        this.router.navigateByUrl("home");
      },
      error: (error) => {
        console.error("error in concertComponent.save()  " + error);
      }
    });

  }

  generateConcertActs() {
    this.generateHeadlinerAct();
    this.generateSupportingActs();
  }

  generateSupportingActs() {
    for(let i = 0; i < this.bands.length; i++) {
      console.log(this.bands[i].name)

      this.newConcert.bands.push(new ConcertAct(null, i + 2, this.bands[i]));
    }
  }
  generateHeadlinerAct() {
    console.log(this.headliner);
    this.newConcert.bands.push(new ConcertAct(null, 1, this.headliner.name));
  }

  stringifyTime(time: any) : string{

    let minuteString = time.minute + "";
    let hourString = time.hour + "";

    if(minuteString.length < 2) {
      minuteString = "0" + minuteString;
    }
    if(hourString.length < 2) {
      hourString = "0" + hourString;
    }

    let timeString = hourString + ":" + minuteString;
    return timeString;
  }

  stringifyDate(date: any) : string {


    return date.year + "-" + date.month + "-" + date.day;

  }


}
