export class Product {
  id: number;
  name: string;
  description: string;
  promo: boolean;
  promoPercentage: number;
  unitsSold: number;

  activationDate: string;
  expireDate: string;
  dateFormat: string;

  priceAmount: number;
  priceCurrency: number;

  categoryOfProduct: number;
  categoryOfProductText: string;

  attributes: Array<[string, any]>;

  quantity: number;
  quantityStandard: number;
  quantityStandardText: string;

  producer: number;
  producerText: string;

  pictures: Array<number>;
}
