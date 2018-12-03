export class Vendor {
  id: number;
  imgUrl: string;
  about: string;
  displayName: string;
  isActive: boolean;

  constructor(id?: number,
    imgUrl?: string,
    about?: string,
    displayName?: string,
    isActive?: boolean) {
    this.id = id;
    this.imgUrl = imgUrl;
    this.about = about;
    this.displayName = displayName;
    this.isActive = isActive;
  }


}
