import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import {FormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ApiService } from './shared';
import { routing } from './app.routing';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { removeNgStyles, createNewHosts } from '@angularclass/hmr';

import { TRANSLATION_PROVIDERS, TranslatePipe, TranslateService }   from './translate';
import {ProductService} from "./shared/services/product.service";
import {AttributesComponent} from "./attributes/attributes.component";
import {CategoriesComponent} from "./categories/categories.component";
import {SharedDataService} from "./shared/services/sharedData.service";
import {ProductCategoryService} from "./shared/services/productCategory.service";


@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    routing,
    NgbModule.forRoot()
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    TranslatePipe,
    CategoriesComponent,
    AttributesComponent
  ],
  providers: [
    ApiService,
    TRANSLATION_PROVIDERS,
    TranslateService,
    ProductService,
    SharedDataService,
    ProductCategoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(public appRef: ApplicationRef) {}
  hmrOnInit(store) {
    console.log('HMR store', store);
  }
  hmrOnDestroy(store) {
    let cmpLocation = this.appRef.components.map(cmp => cmp.location.nativeElement);
    // recreate elements
    store.disposeOldHosts = createNewHosts(cmpLocation);
    // remove styles
    removeNgStyles();
  }
  hmrAfterDestroy(store) {
    // display new elements
    store.disposeOldHosts();
    delete store.disposeOldHosts;
  }
}
