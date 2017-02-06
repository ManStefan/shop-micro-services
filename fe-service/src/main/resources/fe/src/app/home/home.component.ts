import { Component, OnInit } from '@angular/core';

declare var $:any;

@Component({
  selector: 'my-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  constructor() {
    // Do stuff
  }

  ngOnInit() {
    console.log('Hello Home');
  }

  gotoDetail(): void {
    $('.row-offcanvas').toggleClass('active');
  }
}
