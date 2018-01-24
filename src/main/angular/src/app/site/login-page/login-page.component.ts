import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user-service.service';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(private userService: UserService,private router:Router) { }
  private fullImagePath = '/assets/images-hello-site.jpg'
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

  loginUser() {
    this.refreshRegisterSukcess();
    this.message = null
    this.valid = false;
    console.log(this.userName)
    console.log(this.password)
    return this.userService.loginUser(this.userName, this.password).subscribe(res => {
      if (res.statusText == "Not Found") {
        this.message = "Nie znalezionego takiego uzytkownika"
      }
    if(res.text() == "Wrong password"){
      this.message = "Złe hasło!"
    }
      if (res.statusText == "OK") {
          this.userService.saveToken(res.text())
          this.router.navigate(['/home'])
      }

    });

  }

  refreshRegisterSukcess() {
    this.userService.freshRegister = false;
    this.registerSukcess = false;
  }

}
