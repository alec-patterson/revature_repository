import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AddProductComponent } from './products/add-product/add-product.components';
import { ProductComponent } from './products/product.component';
import { ProductsComponent } from './products/products.component';
import { ShortenPipe } from './shorten.pipe';
import { SignupComponent } from './signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CatalogComponent } from './catalog/catalog.component';
import { ProjectinfoComponent } from './catalog/projectinfo/projectinfo.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { ApploginComponent } from './site/applogin/applogin.component';
import { AuthGuard } from './auth.guard';

const routes:Routes = [
  {path:'home', component:HomeComponent},
  {path:'catalog/:id', component:CatalogComponent,
    children: [
      {path:'productinfo/:id', component:ProjectinfoComponent}
    ]
  },
  {path: 'app-login', component: ApploginComponent},
  {path: 'not-found', component:NotfoundComponent},
  {path: '**', redirectTo:'not-found'}
]

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    ProductsComponent,
    AddProductComponent,
    ShortenPipe,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    CatalogComponent,
    ProjectinfoComponent,
    NotfoundComponent,
    ApploginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
