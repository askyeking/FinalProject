import { Band } from "./band";
import { Concert } from "./concert";
import { ConcertActId } from "./concert-act-id";

export class ConcertAct {

 id: ConcertActId | null;
 lineupPosition: number;
 band: Band | null;

 constructor(
  id: ConcertActId | null = null,
  lineupPosition: number = 0,
  band: Band | null = null
 ) {
  this.id = id;
  this.lineupPosition = lineupPosition;
  this.band = band;
 }
}
