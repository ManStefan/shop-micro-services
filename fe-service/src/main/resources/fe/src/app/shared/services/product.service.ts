import { Injectable } from '@angular/core';
import {Product} from "../entities/product";
import {Http} from "@angular/http";

@Injectable()
export class ProductService {

  // private headers = new Headers({'Content-Type': 'application/json'});
  private productsUrl = 'http://localhost:8181/product/newest/';

  constructor(private http: Http) { }

  getNewestProducts(n: number): Promise<Product[]> {
    return this.http.get(this.productsUrl + n)
      .toPromise()
      .then(response => response.json() as Product[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
