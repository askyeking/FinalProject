import { ItemRental } from './item-rental';
import { Customer } from './customer';
export class RatingOfVendor {
  id: number;
  rating: number;
  comment: string;
  itemRental: ItemRental;
  customer: Customer;

  constructor(
    id?: number,
    rating?: number,
    comment?: string,
    itemRental?: ItemRental,
    customer?: Customer
  ) {
    this.id = id;
    this.rating = rating;
    this.comment = comment;
    this.itemRental = itemRental;
    this.customer = customer;
  }
}
