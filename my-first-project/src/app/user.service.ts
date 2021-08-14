import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  isLoggedIn:boolean = false;

  constructor() { }

  authenticate(username:string, password:string) {
    if(username === 'mike') 
      this.isLoggedIn = true;

  }

}
