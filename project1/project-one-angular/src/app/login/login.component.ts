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

    // Used for telling the user if the login was unsuccessful
    invalid:boolean = false;

    OnInit() {
    }

    // performs a post request to confirm if the provided login credentials are correct or not
    // if correct the role and login id of the user will be sent back and saved to local storage,
    // additionally the email will also be saved to the local storage
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

    // if the user clicks on the create new account link then they will be redirected
    // to the appropriate site
    createAccount() {
        this.router.navigate(['createNewAccount']);
    }
}