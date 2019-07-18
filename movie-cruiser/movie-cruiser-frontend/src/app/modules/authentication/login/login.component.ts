import { Component, OnInit } from '@angular/core';
import { user } from './../user';
import { AuthenticationService } from './../authentication.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  newUser: user;

  constructor(private authenticatinService: AuthenticationService, private router: Router) { 
    this.newUser = new user();
  }

  ngOnInit() {
  }

  loginUser() {
    console.log("New User: " + JSON.stringify(this.newUser));   
    this.authenticatinService.loginUser(this.newUser)
      .subscribe((data) => {
        console.log(data);
        this.authenticatinService.setToken(data['token']);
        this.router.navigate(['/movies/popular']);
      });
  }

}
