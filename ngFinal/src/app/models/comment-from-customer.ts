import { Customer } from "./customer";
import { ItemRental } from "./item-rental";

export class CommentFromCustomer {
  id: number;
  customer: Customer;
  itemRental: ItemRental;
  comment: string;
  postDate: Date;

  constructor(
    id?: number,
    customer?: Customer,
    itemRental?: ItemRental,
    comment?: string,
    postDate?: Date,
  ) {
    this.id = id;
    this.customer = customer;
    this.itemRental = itemRental;
    this.comment = comment;
    this.postDate = postDate;
  }
}
