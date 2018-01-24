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
    headers.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/TestProject/api/user/', {
      username: username,
      password: password
    }, options)
  }

  loginUser(username: string, password: string){
    let headers = new Headers({ 'Content_Type': 'application/json' });
    let options = new RequestOptions({ headers: headers })
    headers.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/TestProject/api/user/login', {
      username: username,
      password: password
    }, options) ;
    }

    logout(){
      localStorage.removeItem('auth_token')
    }

    saveToken(tokenBody:string){
      localStorage.setItem('auth_token',tokenBody)
    }
    
    isLoggedIn():boolean{
        return !!localStorage.getItem('auth_token')
    }
  }

