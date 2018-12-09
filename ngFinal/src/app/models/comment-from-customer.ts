import { Customer } from "./customer";
import { ItemRental } from "./item-rental";

export class CommentFromCustomer {
  id: number;
  poster: Customer;
  itemRental: ItemRental;
  comment: string;
  postDate: Date;

  constructor(
    id?: number,
    poster?: Customer,
    itemRental?: ItemRental,
    comment?: string,
    postDate?: Date,
  ) {
    this.id = id;
    this.poster = poster;
    this.itemRental = itemRental;
    this.comment = comment;
    this.postDate = postDate;
  }
}
