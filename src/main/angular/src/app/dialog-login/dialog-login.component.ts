import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-dialog-login',
  templateUrl: './dialog-login.component.html',
  styleUrls: ['./dialog-login.component.css']
})
export class DialogLoginComponent implements OnInit {

  constructor(private userService:UserServiceService) { }

  ngOnInit() {
  }

  loginUser(){
    this.userService.loginUser('karol','policja') ;
  }
}
