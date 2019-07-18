import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  userEndpoint: string;
  token: string;

  constructor(private http: HttpClient) { 
    this.userEndpoint='http://localhost:8089/api/v1/user'
   } 
  

  registerUser(user) {
    const registerUrl = `${this.userEndpoint}/register`; 
    return this.http.post(registerUrl, user,{responseType: 'text'});
  }

  loginUser(user) {
    const loginUrl = `${this.userEndpoint}/login`; 
    return this.http.post(loginUrl, user);
  }

  setToken(token: string) {
    return localStorage.setItem("jwt_token",token);
  }

  getToken() {
    console.log('Fetching token');
    return localStorage.getItem("jwt_token");
  }

  deleteToken() {
    return localStorage.removeItem("jwt_token");
  }

  isTokenExpired(token?: string) {
    if(!token) token = this.getToken();
    if(!token) return true;
    const date = this.getTokenExpirationDate(token);
    if(date === undefined || date ===null) return false;
    return !(date.valueOf() > new Date().valueOf());
  }

  getTokenExpirationDate(token: string) {
    const decoded = jwt_decode(token);
    if(decoded.exp === undefined) return null;
    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

}
