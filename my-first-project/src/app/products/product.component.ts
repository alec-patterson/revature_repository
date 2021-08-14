import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
// import { EventEmitter } from "stream";


@Component({
    selector: 'app-product',
    template: `
        <div>
            <h3>{{title | uppercase | lowercase}}</h3>
            <p>{{price | currency : 'EUR'}}</p>
            <p>{{supplier.name | shorten}}</p>

            <button (click)="removeHandler()">Remove</button>
        </div>
    `
})
export class ProductComponent implements OnInit{
    @Input() title:string = '';
    @Input() price:string = '';
    @Input() supplier:any;
 
    //Instantiation phase
    constructor() {
        console.log('Instantiation phase')
    }

    ngOnInit() {
        console.log('Initialization phase')
    }

    ngOnDestroy() {
        console.log('Destroy phase')
    }

    @Output() remove:EventEmitter<any> = new EventEmitter<any>();

    removeHandler() {
        this.remove.emit(this.title);
    }
}