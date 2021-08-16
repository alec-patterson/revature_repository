import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router:Router){}

  // this AuthGuard file checks if the user is logged in before redirecting to specific urls
  // this is done by checking if an email is saved in the localStorage
  // which is done after a successful login
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      
      const name = localStorage.getItem("email");

      if(name)
        return true;
        
      this.router.navigate(['login'])
      return false;
  }
}