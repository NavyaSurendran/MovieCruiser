import { Injectable } from '@angular/core';
import { 
    HttpClient,
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpResponse
} from '@angular/common/http';
import { map, retry } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';
import { AuthenticationService } from '../authentication/authentication.service';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(private authenticationService: AuthenticationService) { 
  
   }

   intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('Intercepting');
        request = request.clone({
            setHeaders: {
                Authorization: `Bearer ${this.authenticationService.getToken()}`
            }
        });
        console.log(request);
        return next.handle(request);
    }
  
}
