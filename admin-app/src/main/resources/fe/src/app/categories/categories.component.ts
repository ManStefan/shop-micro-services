import { Component, OnInit } from '@angular/core';
import {SharedDataService} from "../shared/services/sharedData.service";

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import {ProductCategoryService} from "../shared/services/productCategory.service";
import {ProductCategory} from "../shared/entities/productCategory";

@Component({
  selector: 'categories',
  templateUrl: './categories.component.html'
})
export class CategoriesComponent implements OnInit {

  parent_cat_model: any;
  model: any;
  searching = false;
  searchFailed = false;
  searchingParent = false;
  searchFailedParent = false;

  userSelectedItem : ProductCategory;
  showSelectedParentValue: string;

  showSearchValue = function(productCategory){
    return productCategory.name + "(id:" + productCategory.id + ") ";
  }

  selectCategory(event){
    this.userSelectedItem = event.item;

    if (this.userSelectedItem.parentCategory != null){
      this.productCategoryService
            .searchProductCategoryById(this.userSelectedItem.parentCategory).subscribe(data =>{
          this.userSelectedItem.parentCategoryName = data.name;
          this.showSelectedParentValue = this.userSelectedItem.parentCategoryName + "(id:" + this.userSelectedItem.parentCategory + ") ";
          });
    }
  }

  selectParentCategory(event){
    this.userSelectedItem.parentCategory = (<ProductCategory>event.item).id;
    this.userSelectedItem.parentCategoryName = (<ProductCategory>event.item).name;

    this.showSelectedParentValue = this.userSelectedItem.parentCategoryName + "(id:" + this.userSelectedItem.parentCategory + ") ";
  }

  deselectParentCategory(){
    this.userSelectedItem.parentCategory = null;
    this.userSelectedItem.parentCategoryName = null;
    this.showSelectedParentValue = null;
  }

  deselectCategory(){
    this.userSelectedItem = null;
  }

  updateCategory() {
    // alert("asdfasdfa");
    this.productCategoryService.putProductCategory(this.userSelectedItem).subscribe();
   }


  search = (text$: Observable<string>) =>
    text$
      .debounceTime(300)
      .distinctUntilChanged()
      .do(() => this.searching = true)
      .switchMap(term => {
          return this.productCategoryService.searchProductCategory(term)
            .do(() => this.searchFailed = false)
            .catch(() => {
              this.searchFailed = true;
              return Observable.of([]);
            })
        }
      )
      .do(() => this.searching = false);

  searchParent = (text$: Observable<string>) =>
    text$
      .debounceTime(300)
      .distinctUntilChanged()
      .do(() => this.searchingParent = true)
      .switchMap(term =>
        this.productCategoryService.searchProductCategory(term)
          .do(() => this.searchFailedParent = false)
          .catch(() => {
            this.searchFailedParent = true;
            return Observable.of([]);
          })
      )
      .do(() => this.searchingParent = false);

  constructor(private sharedDataService: SharedDataService,
              private productCategoryService: ProductCategoryService) { }

  ngOnInit() {
    console.log('Hello Category');
    this.sharedDataService.setCurrentPage("categories");
  }

}
