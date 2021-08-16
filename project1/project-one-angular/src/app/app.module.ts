import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { CreateAccountComponent } from './create-account/create-account.component';
import { FormsModule } from '@angular/forms';
import { AuthGuard } from './login/auth.guard';
import { HomeComponent } from './home/home.component';
import { AddrequestComponent } from './home/addrequest/addrequest.component';
import { GetrequestComponent } from './home/getrequest/getrequest.component';
import { GetallrequestsComponent } from './home/getAllrequests/getallrequests.component';
import { PersonalComponent } from './home/personal/personal.component';

/* 
 * All paths that the front-end application takes
 * AuthGuard is used to prevent any webpage that accesses information
 * from being reached by redirecting to the login page if the user
 * is not logged in 
 */
const routes:Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'home', component:HomeComponent, canActivate: [AuthGuard], children: [
    {path:'addrequest', component:AddrequestComponent},
    {path:'getrequest', component:GetrequestComponent},
    {path:'getall', component:GetallrequestsComponent},
    {path:'getpersonal', component:PersonalComponent}
  ]},
  {path:'createNewAccount', component:CreateAccountComponent},
  {path:'login', component:LoginComponent},
  {path:'**', component:HomeComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreateAccountComponent,
    HomeComponent,
    AddrequestComponent,
    GetrequestComponent, 
    GetallrequestsComponent,
    PersonalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
