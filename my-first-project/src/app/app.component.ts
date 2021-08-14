import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  // template: `
  //   <h2>This is rendered from class</h2>
  //   {{title}}
  // `,
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'my-first-project';
  inactive=false;
  country = 'USA'
  addProduct:boolean = true;

  vehicles = ['Car', 'Bus', 'Train']

  picture='somenice.jpg'
  
  getTitle() {
    return this.title;
  }

  sayHello(){
    console.log("hello");
  }


  onRemoveProduct(e:Event) {
    console.log(e)
  }

  supplier = {
    name: 'Amazon'
  }

}
