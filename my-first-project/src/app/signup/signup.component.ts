import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  username:string = ''
  password:string = ''
  email:string = ''

  constructor() { }

  ngOnInit(): void {
  }

  onUsernameChange(e:any) {
    this.username = e.target.value;
  }

  onPasswordChange(e:any) {
    this.password = e.target.value;
  }

  onEmailChange(e:any) {
    this.email = e.target.value;
  }

  onSubmit(form:NgForm) {
    //this.http.post('',{}).subscription()
    console.log('form submitted', form);
    console.log(form.value);
  }

}
