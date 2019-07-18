import { Component, OnInit } from '@angular/core';
import { user } from './../user';
import { AuthenticationService } from './../authentication.service';
import { Router } from '@angular/router'


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: user;

  constructor(private authenticatinService: AuthenticationService, private router: Router) { 
    this.newUser = new user();
  }

  ngOnInit() {
  }

  registerUser() {
    console.log("New User: " + JSON.stringify(this.newUser));   
    this.authenticatinService.registerUser(this.newUser)
      .subscribe((data) => {
        console.log(data);
        this.router.navigate(['user/login']);
      });
  }

}
