import { Component, OnInit } from '@angular/core';

import { MatDialog } from '@angular/material/dialog';
import { UserService } from '../services/user-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userService: UserService,private router:Router) { }

  ngOnInit() {
  }

  isLoggedIn() {
    return this.userService.isLoggedIn()
  }

  logout() {
    this.userService.logoutUser() ;
    this.router.navigate(['/home'])
  }

  getUserName() {
    return this.userService.getLoggedUserName()
  }
}
