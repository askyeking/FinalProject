import { CommentFromCustomer } from './comment-from-customer';
import { Customer } from './customer';
import { InventoryItem } from './inventory-item';
import { CommentFromVendor } from './comment-from-vendor';
export class ItemRental {
  id: number;
  paid: boolean;
  startDate: Date;
  endDate: Date;
  paidAmount: number;
  active: boolean;
  transactionInfo: string;
  customer: Customer;
  inventoryItem: InventoryItem;
  customerComments: CommentFromCustomer[];
  vendorComments: CommentFromVendor[];



constructor(
  id?: number,
  paid?: boolean,
  startDate?: Date,
  endDate?: Date,
  paidAmount?: number,
  active?: boolean,
  transactionInfo?: string,
  customerComments?: CommentFromCustomer[],
  vendorComments?: CommentFromVendor[],
) {
  this.id = id;
  this.paid = paid;
  this.startDate = startDate;
  this.endDate = endDate;
  this.paidAmount = paidAmount;
  this.active = active;
  this.transactionInfo = transactionInfo;
  this.customerComments = customerComments;
  this.vendorComments = vendorComments;
}


}
