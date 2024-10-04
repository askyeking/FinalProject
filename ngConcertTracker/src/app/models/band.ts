import { Concert } from "./concert";

export class Band {

  id: number;
  name: string;
  imageUrl: string;
  genres: string;
  concerts: Concert[];

  constructor(
    id: number = 0,
    name: string = '',
    imageUrl: string = '',
    genres: string = '',
    concerts: Concert[] = []
  ) {
    this.id = id;
    this.name = name;
    this.imageUrl = imageUrl;
    this.genres = genres;
    this.concerts = concerts;
  }

}
