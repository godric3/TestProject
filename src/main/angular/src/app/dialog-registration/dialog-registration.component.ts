import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-dialog-registration',
  templateUrl: './dialog-registration.component.html',
  styleUrls: ['./dialog-registration.component.css']
})
export class DialogRegistrationComponent implements OnInit {

  constructor(private userService:UserServiceService) { }

  ngOnInit() {
  }
  registerUser(){
    this.userService.registerUser('karol','policja').subscribe(res =>{
      if(res)
        console.log(res.statusText)
    })
  }


}
