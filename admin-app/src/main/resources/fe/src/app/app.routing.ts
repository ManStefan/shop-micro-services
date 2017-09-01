import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import {CategoriesComponent} from "./categories/categories.component";
import {AttributesComponent} from "./attributes/attributes.component";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent},
  { path: 'categories', component: CategoriesComponent},
  { path: 'attributes', component: AttributesComponent}
];

export const routing = RouterModule.forRoot(routes);
