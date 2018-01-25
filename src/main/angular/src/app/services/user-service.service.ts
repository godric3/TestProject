import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { User } from '../models/user';
import { Http, RequestOptions, Headers } from '@angular/http'


@Injectable()
export class UserService {

  constructor(private http: Http) { }
  public freshRegister = false;

  registerUser(username: string, password: string) {
    let headers = new Headers({ 'Content_Type': 'application/json' });
    let options = new RequestOptions({ headers: headers })
    //headers.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/TestProject/api/user/', {
      username: username,
      password: password
    }, options)
  }

  loginUser(username: string, password: string) {
    let headers = new Headers({ 'Content_Type': 'application/json' });
    let options = new RequestOptions({ headers: headers })
   // headers.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/TestProject/api/user/login', {
      username: username,
      password: password
    }, options);
  }

  logoutUser() {
    let headers = new Headers({ 'Authorization': this.getToken()});
    let options = new RequestOptions({ headers: headers })
    localStorage.removeItem('auth_token')
    return this.http.delete('http://localhost:8080/TestProject/api/user/login',options)
    // post delete token
  }

  saveToken(tokenBody: string) {
    localStorage.setItem('auth_token', tokenBody)
  }

  getToken(){
    return localStorage.getItem('auth_token')
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('auth_token')
  }

  setUsernNameFromToken(token: string) {
    let index = token.indexOf("|");
    if (index > 0)
      token = token.substring(0, index);
    return token
  }

  getLoggedUserName() {
    if (!!localStorage.getItem('auth_token')) {
      let token = localStorage.getItem('auth_token');
      let nameUser = this.setUsernNameFromToken(token)
      return nameUser
    } else 
    return null
  }
}

