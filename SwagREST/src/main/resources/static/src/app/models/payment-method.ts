import { PaymentType } from './payment-type';
import { Customer } from './customer';
export class PaymentMethod {

  id: number;
  name: string;
  acctNo: string;
  cvc: string;
  expirationDate: string;
  cardHolderName: string;

  customer: Customer;
  paymentType: PaymentType;


  constructor(
    id?: number,
    name?: string,
    cvc?: string,
    expirationDate?: string,
    cardHolderName?: string
  ){
    this.id = id;
    this.name = name;
    this.cvc = cvc;
    this.expirationDate = expirationDate;
    this.cardHolderName = cardHolderName;
  }




}
