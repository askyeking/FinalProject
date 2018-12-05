export class InventoryItem {
  id: number;
  price: number;
  condition: string;
  name: string;
  description: string;
  imgUrl: string;
  active: boolean;
  rented: boolean;


  constructor(
    id?: number,
    price?: number,
    condition?: string,
    name?: string,
    description?: string,
    imgUrl?: string,
    active?: boolean,
    rented?: boolean
  ) {
    this.id = id;
    this.price = price;
    this.condition = condition;
    this.name = name;
    this.description = description;
    this.imgUrl = imgUrl;
    this.active = active;
    this.rented = rented;

  }

}
