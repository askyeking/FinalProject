import { Vendor } from './vendor';
import { ItemRental } from './item-rental';
import { post } from 'selenium-webdriver/http';
export class CommentFromVendor {
  id: number;
  vendor: Vendor;
  itemRental: ItemRental;
  comment: string;
  postDate: Date;

  constructor(
    id?: number,
    vendor?: Vendor,
    itemRental?: ItemRental,
    comment?: string,
    postDate?: Date,
  ) {
    this.id = id;
    this.vendor = vendor;
    this.itemRental = itemRental;
    this.comment = comment;
    this.postDate = postDate;
  }
}
