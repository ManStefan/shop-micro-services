import { Component, OnInit } from '@angular/core';
import {SharedDataService} from "../shared/services/sharedData.service";

@Component({
  selector: 'attributes',
  templateUrl: './attributes.component.html'
})
export class AttributesComponent implements OnInit {

  constructor(private sharedDataService: SharedDataService) {
    // Do stuff
  }

  ngOnInit() {
    console.log('Hello Attributes');
    this.sharedDataService.setCurrentPage("attributes");
  }

}
