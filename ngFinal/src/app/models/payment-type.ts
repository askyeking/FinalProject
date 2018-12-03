import { VendorPaymentType } from './vendor-payment-type';
import { PaymentMethod } from './payment-method';
export class PaymentType {
  id: number;
  type: string;
  provider: string;
  paymentMethods: PaymentMethod[] = [];
  vendorPaymentMethods: VendorPaymentType[] = [];


  constructor(
    id?: number,
    type?: string,
    provider?: string,
    paymentMethods?: PaymentMethod[],
    vendorPaymentMethods?: VendorPaymentType[]
  ) {
    this.id = id;
    this.type = type;
    this.provider = provider;
    this.paymentMethods = paymentMethods;
    this.vendorPaymentMethods = vendorPaymentMethods;

  }




}
