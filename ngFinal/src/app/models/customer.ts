import { ItemRental } from './item-rental';
import { User } from "./user";

export class Customer {
  id: number;
  displayName: string;
  isActive: boolean;
  avatarURL: string;
  user: User;
  rentedItems: ItemRental[];

  constructor(
    id?: number,
    isActive?: boolean,
    displayName?: string,
    avatarURL?: string,
    rentedItems?: ItemRental[]
  ) {
    this.id = id;
    this.isActive = isActive;
    this.displayName = displayName;
    this.avatarURL = avatarURL;
    this.rentedItems = rentedItems;
  }
}
