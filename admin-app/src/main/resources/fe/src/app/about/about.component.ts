import { Component, OnInit } from '@angular/core';
import {SharedDataService} from "../shared/services/sharedData.service";

@Component({
  selector: 'my-about',
  templateUrl: './about.component.html'
})
export class AboutComponent implements OnInit {

  constructor(private sharedDataService: SharedDataService) {
    // Do stuff
  }

  ngOnInit() {
    console.log('Hello About');
    this.sharedDataService.setCurrentPage("about");
  }

}
