import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatCardModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { By } from '@angular/platform-browser';
import { Location } from '@angular/common';

const testConfig = {
  userData: {
      firstname: 'test',
      lastname: 'testlast',
      userId: 'testuser',
      password: 'pass'
    }
  }

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthenticationService;
  let spyUser:any;
  let routes: Router;
  let location: Location;


  class AuthServiceStub {
    currentUser:any;
    constructor(){

    }


    login(credentials) {
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
        LoginComponent
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
  fixture = TestBed.createComponent(LoginComponent);
  location = TestBed.get(Location);
  component = fixture.componentInstance;
  fixture.detectChanges();
  fixture.debugElement.injector.get(AuthenticationService);
  
});

  it('it should create app component ', async() => {
   const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it('it should contain two text boxes for login  ', () => {
    let userId = fixture.debugElement.query(By.css('.userId'));
    let password = fixture.debugElement.query(By.css('.password'));
    let resgitserButton = fixture.debugElement.query(By.css('.registerButton'));
    let loginButton = fixture.debugElement.query(By.css('.loginButton'));

    let userIdInput = userId.nativeElement;
    let passwordInput = password.nativeElement;
    let resgitserButtonInput = resgitserButton.nativeElement;
    let loginButtonInput = loginButton.nativeElement;
    
    expect(userIdInput).toBeTruthy();
    expect(passwordInput).toBeTruthy();
    expect(resgitserButtonInput).toBeTruthy();
    expect(loginButtonInput).toBeTruthy(); 

   });



   it('it should redirect to login if registered successfully  ', async() => {
    let userId = fixture.debugElement.query(By.css('.userId'));
    let password = fixture.debugElement.query(By.css('.password'));
       let loginButton = fixture.debugElement.query(By.css('.loginButton'));

    let userIdInput = userId.nativeElement;
    let passwordInput = password.nativeElement;
    let loginButtonInput = loginButton.nativeElement;
    fixture.whenStable().then(()=> {
      userIdInput.value = 'testuser';
      userIdInput.value = 'pass';
    }).then(()=> {
      expect(location.path()).toBe('');
    })
    

   });


});
