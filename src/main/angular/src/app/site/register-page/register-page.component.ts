import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  constructor(private userService: UserService, private router:Router) { }

  private fullImagePath = '/assets/images-hello-site.jpg'
  userName: string;
  password: string;

  ngOnInit() {
    this.userName = ''
    this.password = ''
  }

  registerUser(){
    this.userService.registerUser(this.userName,this.password).subscribe(res =>{
      if(res && res.statusText=="OK"){
        this.userService.freshRegister = true
        this.router.navigate(['/login'])

      }
    })
  }

}
