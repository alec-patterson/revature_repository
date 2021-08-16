import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router:Router) { }

  // email and role are grabbed from local storage
  // email is displayed in the welcome message and 
  // role is used to determine what functionality the 
  // user gets
  email:any = localStorage.getItem("email");
  role:any = localStorage.getItem("role");

  ngOnInit(): void {
  }


  // logout will clear the localStorage which will reactivate
  // AuthGuard and redirect the user back to the login page
  logout() {
    localStorage.clear();
    this.router.navigate(['login']);
  }
}
