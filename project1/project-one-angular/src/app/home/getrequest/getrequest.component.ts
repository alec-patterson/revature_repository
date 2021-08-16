import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-getrequest',
  templateUrl: './getrequest.component.html',
  styleUrls: ['./getrequest.component.css']
})
export class GetrequestComponent implements OnInit {

  employee:any;
  // run:boolean = false;

  constructor(private router:Router, private http:HttpClient) { }

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

  formatDate(d:any) {
    var f = new Date(Number(d));
    return f.getMonth() + "/" + f.getDate() + "/" + f.getFullYear();
  }
}
