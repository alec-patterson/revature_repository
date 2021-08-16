import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component ({
    selector: 'app-getall',
    templateUrl: './getallrequests.component.html',
    styleUrls: ['./getallrequests.component.css']
})
export class GetallrequestsComponent implements OnInit {

  requests:any;

  filter:string = "";

  constructor(private router:Router, private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:9080/reimbursement/getAllRequests')
    .subscribe({
      next: (data:any) => {
        this.requests = data;
      }
    })
  }

  onfilterChange(e:any) {
    this.filter = e.target.value;
  }

  changeStatus(id:any, status:any) {
    this.http.post('http://localhost:9080/reimbursement/getAllRequests', 
    JSON.stringify({requestId:Number(id), status:String(status)}))
    .subscribe({
        next: (data:any) => {
            if(data !== null) {
                this.requests = data;
            }
        }
    })
  }

  formatDate(d:any) {
    var f = new Date(Number(d));
    return f.getMonth() + "/" + f.getDate() + "/" + f.getFullYear();
  }

  onSubmitFilter() {

  }

}