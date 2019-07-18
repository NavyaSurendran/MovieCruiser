import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthenticationService } from './modules/authentication/authentication.service';



@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router,private authenticationService: AuthenticationService) { 

    }

    canActivate(){
        if(!this.authenticationService.isTokenExpired()) {
            return true;
        }
        this.router.navigate(['user/login']);
        return false;
    }
   
  
}
