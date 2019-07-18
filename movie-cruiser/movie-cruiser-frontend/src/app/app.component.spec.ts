import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { Router } from '@angular/router';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
describe('AppComponent', () => {
  

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
      imports:[FormsModule, HttpClientModule, MatFormFieldModule, MatInputModule,
         MatButtonModule, BrowserAnimationsModule, RouterTestingModule, MatToolbarModule,
        ],
      providers:[{provide:AuthenticationService}]
    }).compileComponents();
  }));


  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'Movie Cruiser'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Movie Cruiser');
  }));
 
});
