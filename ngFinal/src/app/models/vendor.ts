import { InventoryItem } from './inventory-item';
import { User } from './user';

export class Vendor {
  id: number;
  user: User;
  imgUrl: string;
  about: string;
  displayName: string;
  active: boolean;
  listedItems: InventoryItem [] = [];

  constructor(id?: number,
    imgUrl?: string,
    about?: string,
    displayName?: string,
    active?: boolean,
    listedItems?: InventoryItem [],
    user?: User) {
    this.id = id;
    this.user = user;
    this.imgUrl = imgUrl;
    this.about = about;
    this.displayName = displayName;
    this.active = active;
    this.listedItems = listedItems;
  }


}
