import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule, NgForm } from "@angular/forms";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css']
})
export class PersonalComponent implements OnInit {

  person:any;
  password1:string = "";
  password2:string = "";
  confirmed:boolean = false;
  confirmedTouched:boolean = false;
  passIncorrect:boolean = false;

  // updates the password1 provided in the npassword text box 
  // password1 is updated as a user types it in
  // this is used for confirming that the new password and 
  // the confirmation of the new password are the same
  onPassChange(e:any) {
    this.password1 = e.target.value;
    if(this.password1 === this.password2)
      this.confirmed = true;
    else
      this.confirmed = false;
  }

  // performs the same as the above function
  onConfirmChange(e:any) {
    this.password2 = e.target.value;
    this.confirmedTouched = true;
    if(this.password1 === this.password2)
      this.confirmed = true;
    else
      this.confirmed = false;
  }

  constructor(private router:Router, private http:HttpClient) { }

  // Upon initialization of this page a post request sent to the server
  // to retrieve the users personal information
  ngOnInit(): void {
    this.http.post('http://localhost:9080/reimbursement/personalInfo',
    JSON.stringify({id:Number(localStorage.getItem("id"))}))
    .subscribe({
        next: (data:any) => {
            this.person = data;
        }
    })
  }

  // onSubmit will take in a form and send a post request to update the users password
  onSubmit(form:NgForm) {
    this.http.post('http://localhost:9080/reimbursement/newPassword',
    JSON.stringify({id:Number(localStorage.getItem("id")), cpassword:form.value.cpassword, npassword:form.value.nPassword}))
    .subscribe({
        next: (data:any) => {
            if(data.success === true) {
                this.passIncorrect = false;
            } else {
                this.passIncorrect = true;
            }
        }
    })
  }

}
