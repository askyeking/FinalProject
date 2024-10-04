export class Venue {

  id: number;
  name: string;
  city: string;
  zip: string;
  street: string;
  imageUrl: string;
  websiteUrl: string;

  constructor(
    id: number = 0,
    name: string = '',
    city: string = '',
    zip: string = '',
    street: string = '',
    imageUrl: string = '',
    websiteUrl: string = ''
  ){
    this.id = id;
    this.name = name;
    this.city = city;
    this.zip = zip;
    this.street = street;
    this.imageUrl = imageUrl;
    this.websiteUrl = websiteUrl;
  }
}
