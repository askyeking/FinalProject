import { Vendor } from './vendor';
import { Customer } from './customer';
export class User {
  id: number;
  email: string;
  password: string;
  active: boolean;
  role: string;
  vendorProfile: Vendor;
  customerProfile: Customer;

  constructor(id?: number,
    email?: string,
    password?: string,
    active?: boolean,
    role?: string,
    vendor?: Vendor,
    customer?: Customer) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.active = active;
    this.role = role;
    this.vendorProfile = vendor;
    this.customerProfile = customer;
  }
}
