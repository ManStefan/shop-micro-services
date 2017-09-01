
import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import {ProductCategory} from "../entities/productCategory";
import {Observable} from "rxjs";

@Injectable()
export class ProductCategoryService {

  private searchProductsUrl = 'http://localhost:8585/category_of_product/name/';
  private getProductCategoryById = 'http://localhost:8585/category_of_product/id/';
  private modifyProductCategory = 'http://localhost:8585/category_of_product';

  constructor(private http: Http) { }

  searchProductCategory(name: string): Observable<ProductCategory[]>{
    if (name != null && name.trim() != '') {
      return this.http.get(this.searchProductsUrl + name)
        .map(response => (response.json() as ProductCategory[]))
        .catch(this.handleError);
    }
    return Observable.empty<ProductCategory[]>();
  }

  searchProductCategoryById(id: number): Observable<ProductCategory>{
    return this.http.get(this.getProductCategoryById + id)
      .map(response => (response.json() as ProductCategory))
      .catch(this.handleError);
  }

  putProductCategory(payload: ProductCategory): Observable<string> {
    // let bodyString = JSON.stringify(payload);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options       = new RequestOptions({ headers: headers });

     // alert(bodyString);

    return this.http.put(this.modifyProductCategory, payload, options)
      .do(resp => alert(resp))// ...using put request
      .catch(this.handleError);

  }

  private handleError(err: any) {
    console.log('sever error:', err);  // debug
    if(err instanceof Response) {
      return Observable.throw(err.json().error || 'backend server error');
      // if you're using lite-server, use the following line
      // instead of the line above:
      //return Observable.throw(err.text() || 'backend server error');
    }
    return Observable.throw(err || 'backend server error');
  }
}
