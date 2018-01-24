import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { User } from '../models/user';
import { Http, RequestOptions, Headers } from '@angular/http'


@Injectable()
export class UserServiceService {

  constructor(private http: Http) { }

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
    this.http.post('http://localhost:8080/TestProject/api/user/login', {
      username: username,
      password: password
    }, options).subscribe(res=>{
      console.log(res.text())

    })
  }
  }
