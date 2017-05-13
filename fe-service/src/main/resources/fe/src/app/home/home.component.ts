import { Component, OnInit } from '@angular/core';
import {ProductService} from "../shared/services/product.service";
import {Product} from "../shared/entities/product";

declare var $:any;

@Component({
  selector: 'my-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getNewestProducts();
  }

  newestProducts: Product[];

  getNewestProducts(): void {
    this.productService.getNewestProducts(3).then(products => {
      this.newestProducts = products;
    });


  }

  gotoDetail(): void {
    $('.row-offcanvas').toggleClass('active');
  }
}
