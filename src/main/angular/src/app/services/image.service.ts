import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, URLSearchParams} from '@angular/http'
import { UserService } from './user-service.service';
import { ResponseContentType } from '@angular/http/src/enums';

import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ImageService {

  constructor(private http: Http,private httpClient: HttpClient,private userService:UserService) { }

  sendImage(data: FormData) {
    let headers = new Headers({ 'Content_Type': 'multipart/form-data' });
    headers.append('Authorization',this.userService.getToken())
    //headers.append('Authorization', 'token')
    let options = new RequestOptions({ headers: headers })
    return this.http.post('http://localhost:8080/TestProject/api/image', data, options)
  }

  getImage(id:string):Observable<any>{
    //let headers = new Headers() 
    //let myParams = new URLSearchParams()
    //headers.append('Authorization',this.userService.getToken())
    //myParams.append('id',id)
    //let options = new RequestOptions({ headers: headers})
    let headers:HttpHeaders = new HttpHeaders().set('Authorization', this.userService.getToken())
    return this.httpClient.get('http://localhost:8080/TestProject/api/image/'+id,{headers:headers,responseType:'blob'})
  }
}
