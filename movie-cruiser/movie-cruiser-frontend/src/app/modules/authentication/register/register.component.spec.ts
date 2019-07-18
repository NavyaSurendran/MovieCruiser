import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RegisterComponent } from './register.component';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { FormsModule } from '@angular/forms';
import {  of } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatCardModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { By } from '@angular/platform-browser';
import { Location } from '@angular/common';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let authService: AuthenticationService;
  let spyUser:any;
  let routes: Router;
  let location: Location;


  const testConfig = {
    userData: {
        firstname: 'test',
        lastname: 'testlast',
        userId: 'testuser',
        password: 'pass'
      }
    }
  


  class AuthServiceStub {
    currentUser:any;
    constructor(){

    }


    register(credentials) {
      if(credentials.userId ==  testConfig.userData.userId) {
        console.log('data', this.currentUser);
        return of(credentials.userId);
      }
      return of(false);
    }
  }

class dummy {

}

 beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations:[
        RegisterComponent
      ],
      imports:[FormsModule,MatCardModule, HttpClientModule, MatFormFieldModule, MatInputModule, MatButtonModule, BrowserAnimationsModule,RouterTestingModule.withRoutes(
        [{path:'',
      component:dummy}]
      )
        ],
      providers:[{provide:AuthenticationService, useClass:AuthServiceStub}]
  }).compileComponents();

 }));

 beforeEach(() => {
  routes = TestBed.get(Router);
  fixture = TestBed.createComponent(RegisterComponent);
  location = TestBed.get(Location);
  component = fixture.componentInstance;
  fixture.detectChanges();
  fixture.debugElement.injector.get(AuthenticationService);
  
});

  it('it should create app component ', async() => {
   const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it('it should contain four text boxes for register  ', () => {
    let userId = fixture.debugElement.query(By.css('.user_id'));
    let password = fixture.debugElement.query(By.css('.password'));
    let firstname = fixture.debugElement.query(By.css('.firstname'));
    let lastname = fixture.debugElement.query(By.css('.lastname'));
    let resgitserButton = fixture.debugElement.query(By.css('.registerButton'));


    let userIdInput = userId.nativeElement;
    let passwordInput = password.nativeElement;
    let firstnameInput = firstname.nativeElement;
    let lastnameInput = lastname.nativeElement;
    let resgitserButtonInput = resgitserButton.nativeElement;
    
    expect(userIdInput).toBeTruthy();
    expect(passwordInput).toBeTruthy();
    expect(firstnameInput).toBeTruthy();
    expect(lastnameInput).toBeTruthy();
    expect(resgitserButtonInput).toBeTruthy();

   });



   it('it should redirect to login if registered successfully  ', async() => {
    let userId = fixture.debugElement.query(By.css('.user_id'));
    let userIdInput = userId.nativeElement;   
    fixture.whenStable().then(()=> {
      userIdInput.value = 'testuser';
      userIdInput.value = 'pass';
    }).then(()=> {
      expect(location.path()).toBe('');
    })
    

   });


});
