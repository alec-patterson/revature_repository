import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { NgForm } from '@angular/forms';

@Component ({
    selector: 'app-getall',
    templateUrl: './getallrequests.component.html',
    styleUrls: ['./getallrequests.component.css']
})
export class GetallrequestsComponent implements OnInit {

  // saves all employee information 
  requests:any;

  // type of filter to be applied to the result requests
  filter:String = "none";

  // filtered list of reimbursement requests
  filtered:any[] = [];

  constructor(private router:Router, private http:HttpClient) { }

  // upon initialization of the page a post request will get all
  // user information for displaying reimbursement requests and will store
  // the information into requests as well as filtered (deep copy)
  ngOnInit(): void {
    this.http.get('http://localhost:9080/reimbursement/getAllRequests')
    .subscribe({
      next: (data:any) => {
        this.requests = data;
        this.filtered = JSON.parse(JSON.stringify(this.requests));
      }
    })
  }


  // will send a post request to change the status of a reimbursement request
  // to either approved or denied
  changeStatus(id:any, status:any) {
    this.http.post('http://localhost:9080/reimbursement/getAllRequests', 
    JSON.stringify({requestId:Number(id), status:String(status)}))
    .subscribe({
        next: (data:any) => {
            if(data !== null) {
                this.requests = data;
                this.reFilter();
            }
        }
    })
  }

  
  // will change the date of a reimbursement request to the proper format
  // to display to the user
  formatDate(d:any) {
    var f = new Date(Number(d));
    return f.getMonth() + "/" + f.getDate() + "/" + f.getFullYear();
  }


  // updateFilter creates a new copy of filtered
  // reimbursement requests will be filtered out depending
  // on the desired request
  updateFilter(form:NgForm) {
    this.filter = form.value.filter;
    if(this.filter === "none") {
      this.filtered = JSON.parse(JSON.stringify(this.requests));
    }
    else {
      this.filtered = JSON.parse(JSON.stringify(this.requests));
      for(let i = 0; i < this.requests.length; i++) {
        this.filtered[i].employeeInfo.requests = [];
        for(let j = 0; j < this.requests[i].employeeInfo.requests.length; j++)
          if(this.requests[i].employeeInfo.requests[j].status === this.filter) {
           this.filtered[i].employeeInfo.requests.push(this.requests[i].employeeInfo.requests[j]);
        }
      }
    }
  }


  // refilter will retrieve the updated list of requests after the approval or denial of
  // a request
  reFilter() {
    if(this.filter === "none") {
      this.filtered = JSON.parse(JSON.stringify(this.requests));
    }
    else {
      this.filtered = JSON.parse(JSON.stringify(this.requests));
      for(let i = 0; i < this.requests.length; i++) {
        this.filtered[i].employeeInfo.requests = [];
        for(let j = 0; j < this.requests[i].employeeInfo.requests.length; j++)
          if(this.requests[i].employeeInfo.requests[j].status === this.filter) {
           this.filtered[i].employeeInfo.requests.push(this.requests[i].employeeInfo.requests[j]);
        }
      }
    }
  }

}