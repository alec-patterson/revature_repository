import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";

@Component ({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent {
    constructor(private http:HttpClient, private router:Router) {}

    invalid:boolean = false;

    OnInit() {
    }

    onSubmit(form:NgForm) {
        this.http.post('http://localhost:9080/reimbursement/login', 
            JSON.stringify({email:form.value.email,password:form.value.password}))
            .subscribe({
            next: (data:any) => {
                if(data.success === true) {
                    localStorage.setItem("email", form.value.email);
                    localStorage.setItem("role", data.role);
                    localStorage.setItem("id", data.loginId);
                    this.router.navigate(['home']);
                }
                else if(data.success === false) {
                    // console.log("here");
                    this.invalid = true;
                }
            }
        })

        
    }

    createAccount() {
        this.router.navigate(['createNewAccount']);
    }
}