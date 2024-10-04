export class ConcertActId {
  concertId: number;
  bandId: number;

  constructor(
    concertId: number = 0,
    bandId: number = 0
  ){
    this.concertId = concertId;
    this.bandId = bandId;
  }
}
