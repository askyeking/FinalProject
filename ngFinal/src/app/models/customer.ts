import { ItemRental } from './item-rental';
import { User } from './user';

export class Customer {
  id: number;
  displayName: string;
  active: boolean;
  avatarURL: string;
  rentedItems;
  user: User;

  constructor(
    id?: number,
    active?: boolean,
    displayName?: string,
    avatarURL?: string,
    rentedItems?,
    user?
  ) {
    this.id = id;
    this.active = active;
    this.displayName = displayName;
    this.avatarURL = avatarURL;
    this.rentedItems = rentedItems;
    this.user = user;
  }
}
