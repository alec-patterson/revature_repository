import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-getrequest',
  templateUrl: './getrequest.component.html',
  styleUrls: ['./getrequest.component.css']
})
export class GetrequestComponent implements OnInit {

  // stores a users information
  // used for accessing a users reimbursement requests
  employee:any;

  constructor(private router:Router, private http:HttpClient) { }


  // creates a post request upon initialization
  // this post request should recieve the users employee information
  ngOnInit(): void {
    // this.run = true;
    this.http.post('http://localhost:9080/reimbursement/getMyRequests',
    JSON.stringify({id:Number(localStorage.getItem("id"))}))
    .subscribe({
      next: (data:any) => {
        this.employee = data;
      }
    })
  }

  
  // Take in the date in a reimbursement request and converts it into 
  // the appropriate format
  formatDate(d:any) {
    var f = new Date(Number(d));
    return f.getMonth() + "/" + f.getDate() + "/" + f.getFullYear();
  }
}
