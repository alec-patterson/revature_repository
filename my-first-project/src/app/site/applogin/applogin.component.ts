import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-applogin',
  templateUrl: './applogin.component.html',
  styleUrls: ['./applogin.component.css']
})
export class ApploginComponent implements OnInit {

  username:string = '';

  constructor(private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    
  }

  onNameChange(e:any) {
    this.username = e.target.value
  }

  onSubmit() {
    console.log('submitted')
    this.userService.authenticate(this.username, '')
    //this.router.navigate(['home'])
  }

}
