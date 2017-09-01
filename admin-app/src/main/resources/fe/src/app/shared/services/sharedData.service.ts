import {Injectable} from "@angular/core";

@Injectable()
export class SharedDataService {

  private currentPage = "home";

  public setCurrentPage(page: string){
    this.currentPage = page;
  }

  public getCurrentPage(){
    return this.currentPage;
  }
}
