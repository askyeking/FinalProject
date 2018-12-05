import { ItemRental } from "./item-rental";

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
  }

}
