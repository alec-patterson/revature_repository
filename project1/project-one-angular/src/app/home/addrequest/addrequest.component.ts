import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from "@angular/forms";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-addrequest',
  templateUrl: './addrequest.component.html',
  styleUrls: ['./addrequest.component.css']
})
export class AddrequestComponent implements OnInit {

  invalid:boolean = false;

  constructor(private router:Router, private http:HttpClient) { }

  ngOnInit(): void {
  }

  // sends a post request with the information provided in the submitted form
  // to create and add a new reimbursement request to the database
  onSubmit(form:NgForm) {
    this.http.post('http://localhost:9080/reimbursement/addRequests',
    JSON.stringify({id:Number(localStorage.getItem("id")), type:form.value.type, description:form.value.description,
    amount:String(form.value.amount)}))
    .subscribe({
      next: (data:any) => {
        if(data.success === true) {
          this.router.navigate(['home']);
        }
        else if(data.success === false) {
          this.invalid = true
        }
      }
  })
  }
}
