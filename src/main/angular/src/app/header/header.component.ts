import { Component, OnInit } from '@angular/core';

import { MatDialog } from '@angular/material/dialog';
import { UserService } from '../services/user-service.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userService:UserService) { }

  ngOnInit() {
  }

  isLoggedIn(){
    return this.userService.isLoggedIn()
  }

  logout(){
    this.userService.logout()
    console.log("Wylogowano")
  }
}
