import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent{

  createAccountForm:any;
  password1:string = "";
  password2:string = "";
  confirmed:boolean = false;
  confirmedTouched:boolean = false;


  // changes password1 anytime an event occurs on the 
  // password input, this is used to confirm that the password
  // and confirmation password are the same
  onPassChange(e:any) {
    this.password1 = e.target.value;
    if(this.password1 === this.password2)
      this.confirmed = true;
    else
      this.confirmed = false;
  }


  // Same functionality as above but on password2
  onConfirmChange(e:any) {
    this.password2 = e.target.value;
    this.confirmedTouched = true;
    if(this.password1 === this.password2)
      this.confirmed = true;
    else
      this.confirmed = false;
  }

  constructor(private http:HttpClient, private router:Router) { }


  // sends a post request with the information from the submitted form to create a new account
  // if successful the new user is automatically logged in
  onSubmit(form:NgForm) {
    this.http.post('http://localhost:9080/reimbursement/createAccount', 
    JSON.stringify({firstName:form.value.firstName, lastName:form.value.lastName, email:form.value.email, password:form.value.password, address:form.value.address, 
      city:form.value.city, state:form.value.state, zipcode:form.value.zipcode, phonenumber:form.value.phonenumber}))
      .subscribe({
        next: (data:any) => {
          if(data.success === true)
            localStorage.setItem("role", data.role);
            localStorage.setItem("email", form.value.email);
            localStorage.setItem("id", data.loginId);
            this.router.navigate(['home']);
        }
      })
  }
}
