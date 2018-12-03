export class InventoryItem {
  id: number;
  price: number;
  condition: string;
  name: string;
  description: string;
  imgUrl: string;
  isActive: boolean;
  isRented: boolean;


  constructor(
    id?: number,
    price?: number,
    condition?: string,
    name?: string,
    description?: string,
    imgUrl?: string,
    isActive?: boolean,
    isRented?: boolean
  ) {
    this.id = id;
    this.price = price;
    this.condition = condition;
    this.name = name;
    this.description = description;
    this.imgUrl = imgUrl;
    this.isActive = isActive;
    this.isRented = isRented;

  }

}
