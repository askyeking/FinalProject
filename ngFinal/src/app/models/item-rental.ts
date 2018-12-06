import { Customer } from './customer';
import { InventoryItem } from './inventory-item';
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



constructor(
  id?: number,
  paid?: boolean,
  startDate?: Date,
  endDate?: Date,
  paidAmount?: number,
  active?: boolean,
  transactionInfo?: string,
) {
  this.id = id;
  this.paid = paid;
  this.startDate = startDate;
  this.endDate = endDate;
  this.paidAmount = paidAmount;
  this.active = active;
  this.transactionInfo = transactionInfo;
}


}
