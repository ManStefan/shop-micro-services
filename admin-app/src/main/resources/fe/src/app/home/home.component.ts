import { Component, OnInit } from '@angular/core';
import {SharedDataService} from "../shared/services/sharedData.service";

@Component({
  selector: 'my-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  constructor(private sharedDataService: SharedDataService) { }

  ngOnInit() {
    this.sharedDataService.setCurrentPage("home");
  }


}
