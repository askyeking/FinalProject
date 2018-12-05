import { InventoryItem } from './inventory-item';

export class Vendor {
  id: number;
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
    listedItems?: InventoryItem []) {
    this.id = id;
    this.imgUrl = imgUrl;
    this.about = about;
    this.displayName = displayName;
    this.active = active;
    this.listedItems = listedItems;
  }


}
