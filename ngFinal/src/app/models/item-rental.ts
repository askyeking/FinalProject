import { Customer } from './customer';
import { InventoryItem } from './inventory-item';
export class ItemRental {
  id: number;
  isPaid: boolean;
  startDate: Date;
  endDate: Date;
  paidAmount: number;
  isActive: boolean;
  transactionInfo: string;
  customer: Customer;
  inventoryItem: InventoryItem;



constructor(
  id?: number,
  isPaid?: boolean,
  startDate?: Date,
  endDate?: Date,
  paidAmount?: number,
  isActive?: boolean,
  transactionInfo?: string
) {
  this.id = id;
  this.isPaid = isPaid;
  this.startDate = startDate;
  this.endDate = endDate;
  this.paidAmount = paidAmount;
  this.isActive = isActive;
  this.transactionInfo = transactionInfo;

}


}
