import {Component, OnInit} from '@angular/core';

import '../style/offcanvas.css';
import {TranslateService} from "./translate/translate.service";
import {SharedDataService} from "./shared/services/sharedData.service";


declare var $:any;

@Component({
  selector: 'my-app', // <my-app></my-app>
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  public supportedLanguages: any[];

  public currentLanguageName: string;

  url = 'https://github.com/preboot/angular2-webpack';
  title: string;

  constructor(private _translate: TranslateService, private sharedDataService: SharedDataService){}

  ngOnInit(): void {
    // standing data
    this.supportedLanguages = [
      { display: 'English', value: 'en' },
      { display: 'Română', value: 'ro' }
    ];

    // set current langage
    this.selectLang('en');
  }


  isCurrentLang(lang: string) {
    // check if the selected lang is current lang
    return lang === this._translate.currentLang;
  }

  selectLang(lang: string) {
    // set current lang;
    this._translate.use(lang);

    for (let i of this.supportedLanguages){
      if (i.value == lang){
        this.currentLanguageName = i.display;
      }
    }

  }

  gotoDetail(): void {
    $('.row-offcanvas').toggleClass('active');
  }

  isCurrentPage(page: string){
    return this.sharedDataService.getCurrentPage() == page;
  }
}
