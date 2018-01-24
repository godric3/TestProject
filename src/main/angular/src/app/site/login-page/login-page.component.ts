import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user-service.service';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { Router } from '@angular/router';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit,OnDestroy {


  constructor(private userService: UserService,private router:Router) { }
  private fullImagePath = '/TestProject/ng/assets/images-hello-site.jpg'
  hide = true;
  userName: string;
  password: string;
  valid: boolean
  registerSukcess: boolean
  message

  ngOnInit() {
    this.userName = ''
    this.password = ''
    this.message = null
    this.valid = false
    this.registerSukcess = this.userService.freshRegister
  }
  ngOnDestroy() {
    this.refreshRegisterSukcess();
  }

  loginUser() {
    this.refreshRegisterSukcess();
    this.message = null
    this.valid = false;
    console.log(this.userName)
    console.log(this.password)
    return this.userService.loginUser(this.userName, this.password).subscribe(res => {
      if (res.statusText == "OK") {
          this.userService.saveToken(res.text())
          this.userService.setUsernNameFromToken(res.text())
          this.router.navigate(['/home'])
      }
      console.log(this.message)
    },
  error =>{
          if (error.statusText == "Not Found") {
        console.log("Not Found");
        this.message = "Nie znalezionego takiego uzytkownika"
      }
    if(error.text() == "Wrong password"){
      console.log("Złe hasło!");
      this.message = "Złe hasło!"
    }
  });

  }

  refreshRegisterSukcess() {
    this.userService.freshRegister = false;
    this.registerSukcess = false;
  }

}
