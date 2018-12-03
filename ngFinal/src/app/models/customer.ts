export class Customer {
  id: number;
  displayName: string;
  isActive: boolean;
  avatarURL: string;

  constructor(
    id?: number,
    isActive?: boolean,
    displayName?: string,
    avatarURL?: string
  ) {
    this.id = id;
    this.isActive = isActive;
    this.displayName = displayName;
    this.avatarURL = avatarURL;
  }
}
