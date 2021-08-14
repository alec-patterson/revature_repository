import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  products = [
    {
      title:'Watch',
      id:'1'
    },
    {
      title:'Monitor',
      id:'2'
    },
    {
      title:'TV',
      id:'3'
    }
  ]

  paramsSubscription:Subscription;

  constructor(private route:ActivatedRoute) {
    this.paramsSubscription = this.route.params.subscribe({
      next: (data)=>{console.log(data)}
    })

    this.route.params.subscribe({
      next: (data)=>{console.log(data)}
    })
   }

  ngOnInit(): void {
    
  }

  ngOnDestroy() {
    this.paramsSubscription.unsubscribe();
  }

}
