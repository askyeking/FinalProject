import { ConcertAct } from "./concert-act";
import { Venue } from "./venue";

export class Concert {


  id: number;
  doorsTime: string;
  startTime: string;
  dayOfEvent: string;
  imageUrl: string;
  ticketPurchaseLink: string;
  websiteUrl: string;
  ageRequirement: string;
  bands: ConcertAct[];
  venue: Venue;

  constructor(
    id: number = 0,
    doorsTime: string = '',
    startTime: string = '',
    dayOfEvent: string = '',
    imageUrl: string = '',
    ticketPurchaseLink: string = '',
    websiteUrl: string = '',
    ageRequirement: string = '',
    bands: ConcertAct[] = [],
    venue: Venue = new Venue()
  ){
    this.id = id;
    this.doorsTime = doorsTime;
    this.startTime = startTime;
    this.dayOfEvent = dayOfEvent;
    this.imageUrl = imageUrl;
    this.ticketPurchaseLink = ticketPurchaseLink;
    this.websiteUrl = websiteUrl;
    this.bands = bands
    this.venue = venue;
    this.ageRequirement = ageRequirement;
  }
}
