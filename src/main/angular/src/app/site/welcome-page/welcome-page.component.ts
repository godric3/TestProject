import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user-service.service';

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css']
})
export class WelcomePageComponent implements OnInit {

  private resultsGet;
  private resultPost;
  private getUrl = "http://localhost:8080/TestProject/api/responseWithHeader";
  private postUrl = "https://jsonplaceholder.typicode.com/posts";
  private fullImagePath = '/assets/images-hello-site.jpg'

  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }

  isLoggedIn(){
    return this.userService.isLoggedIn()
  }
}
