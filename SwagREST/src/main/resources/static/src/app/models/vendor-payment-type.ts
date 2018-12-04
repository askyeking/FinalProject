import { Vendor } from './vendor';
import { PaymentType } from './payment-type';

export class VendorPaymentType {
  id: number;
  vendor: Vendor;
  paymentType: PaymentType;
  acctNo: string;
  expirationDate: string;
  cardHolderName: string;

  constructor(id?: number,
    vendor?: Vendor,
    paymentType?: PaymentType,
    acctNo?: string,
    expirationDate?: string,
    cardHolderName?: string
  ) {
    this.id = id;
    this.vendor = vendor;
    this.paymentType = paymentType;
    this.acctNo = acctNo;
    this.expirationDate = expirationDate;
    this.cardHolderName = cardHolderName;
    }

}
