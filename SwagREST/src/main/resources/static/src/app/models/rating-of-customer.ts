import { Vendor } from './vendor';
import { ItemRental } from './item-rental';
export class RatingOfCustomer {
  id: number;
  rating: number;
  comment: string;
  itemRental: ItemRental;
  vendor: Vendor;


  constructor(
    id?: number,
    rating?: number,
    comment?: string,
    itemRental?: ItemRental,
    vendor?: Vendor
  ){
    this.id = id;
    this.rating = rating;
    this.comment = comment;
    this.itemRental = itemRental;
    this.vendor = vendor;
  }

}
