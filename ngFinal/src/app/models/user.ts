export class User {
  id: number;
  email: string;
  password: string;
  active: boolean;
  role: string;

  constructor(id?: number,
    email?: string,
    password?: string,
    active?: boolean,
    role?: string) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.active = active;
    this.role = role;
  }
}
