import { ItemRental } from "./item-rental";
import { Vendor } from "./vendor";

export class InventoryItem {
  id: number;
  price: number;
  condition: string;
  name: string;
  description: string;
  imgUrl: string;
  active: boolean;
  rented: boolean;
  allRents: ItemRental[];
  vendor: Vendor;


  constructor(
    id?: number,
    price?: number,
    condition?: string,
    name?: string,
    description?: string,
    imgUrl?: string,
    active?: boolean,
    rented?: boolean,
    allRents?: ItemRental[],
    vendor?: Vendor,
  ) {
    this.id = id;
    this.price = price;
    this.condition = condition;
    this.name = name;
    this.description = description;
    this.imgUrl = imgUrl;
    this.active = active;
    this.rented = rented;
    this.allRents = allRents;
    this.vendor = vendor;
  }

}
