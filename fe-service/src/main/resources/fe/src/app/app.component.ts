import { Component } from '@angular/core';

import { ApiService } from './shared';

import '../style/starter-template.css';

@Component({
  selector: 'my-app', // <my-app></my-app>
  templateUrl: './app.component.html'
})
export class AppComponent {
  url = 'https://github.com/preboot/angular2-webpack';
  title: string;

  constructor(private api: ApiService) {
    this.title = this.api.title;
  }
}
