import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http'
import { UserService } from './user-service.service';

@Injectable()
export class ImageService {

  constructor(private http: Http,userService:UserService) { }

  sendImage(data: FormData) {
    let headers = new Headers({ 'Content_Type': 'multipart/form-data' });
    //headers.append('Authorization',this.userService.getToken())
    headers.append('Authorization', 'token')
    let options = new RequestOptions({ headers: headers })
    return this.http.post('http://localhost:8080/TestProject/api/image', data, options)
  }
}
