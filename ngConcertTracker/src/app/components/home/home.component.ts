import { Component, OnInit } from '@angular/core';
import { ConcertService } from '../../services/concert.service';
import { Concert } from '../../models/concert';
import { AuthService } from '../../services/auth.service';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, DatePipe],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  upcomingConcerts: Concert[] = [];

  constructor(private concertService: ConcertService, private auth: AuthService) {}

  ngOnInit(): void {
    this.load();

  }

  displayHeadlinerName(concert: Concert) : string {
    let name = "Placeholder";
    let bands = concert.bands;
    if(bands){
     for(let i = 0; i < bands.length; i++) {
      let band = bands[i].band;
      if(band &&  bands[i].lineupPosition === 1){
        name =  band.name;
      }
     }
    }
    return name;
  }

  load() {
    this.concertService.getUpcomingConcerts().subscribe({
      next: (upcomingConcerts) => {
        this.upcomingConcerts = upcomingConcerts;
        console.log(upcomingConcerts);
      },
      error: (err) => {
        console.error("Error loading upcoming concerts: " + err);
      }
    });
  }


}
