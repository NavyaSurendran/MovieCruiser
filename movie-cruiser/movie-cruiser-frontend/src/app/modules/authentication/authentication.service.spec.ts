import { AuthenticationModule } from './authentication.module';
import { AuthenticationService } from './authentication.service';
import { TestBed, inject, fakeAsync } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';



const testConfig = {
  addUser: {
    positive: {
      firstname: 'test',
      lastname: 'testlast',
      userId: 'testuser',
      password: 'pass'
    }
  },
  loginUser: {
    positive: {      
      userId: 'testuser',
      password: 'pass'
    }
  }
}

describe('AuthenticationService', () => {
  let authenticationService: AuthenticationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule, HttpClientTestingModule],
      providers:[AuthenticationService]
    });

    authenticationService = TestBed.get(AuthenticationService);

  });

  it('should create the authentication servicce', inject([AuthenticationService], (service: AuthenticationService)=> {
      expect(service).toBeTruthy();
  }) );


  it('should register user data', fakeAsync(()=> {
    let data =  testConfig.addUser.positive;
    inject([AuthenticationService, HttpTestingController], (backend: HttpTestingController)=> {
      const mockReq = backend.expectOne(authenticationService.userEndpoint);
      expect(mockReq.request.url).toEqual(authenticationService.userEndpoint, 'request url should match json server url');
      expect(mockReq.request.method).toBe('POST','should handle request method type');

      mockReq.flush(data);
      backend.verify();
    });

    authenticationService.registerUser(data).subscribe((res: any)=> {
      expect(res).toBeDefined();
      expect(res._body).toBe(data,'data should be the same');
    })

  }));


  it('should login user ', fakeAsync(()=> {
    let data =  testConfig.loginUser.positive;
    inject([AuthenticationService, HttpTestingController], (backend: HttpTestingController)=> {
      const mockReq = backend.expectOne(authenticationService.userEndpoint);
      expect(mockReq.request.url).toEqual(authenticationService.userEndpoint, 'request url should match json server url');
      expect(mockReq.request.method).toBe('POST','should handle request method type');

      mockReq.flush(data);
      backend.verify();
    });

    authenticationService.loginUser(data).subscribe((res: any)=> {
      expect(res).toBeDefined();
      expect(res._body).toBe(data,'data should be the same');
    })

  }));


});
