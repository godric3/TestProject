import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  private fullImagePath = '/TestProject/ng/assets/images-hello-site.jpg'
  userName: string;
  password: string;
  validMessage: string;
  ngOnInit() {
    this.userName = ''
    this.password = ''
    this.validMessage = null 
  }

  registerUser() {
    this.validMessage = null 
    if (this.userName.length > 5 && this.password.length > 5) {
      this.userService.registerUser(this.userName, this.password).subscribe(res => {
        if (res.statusText == "OK") {
          this.userService.freshRegister = true
          this.router.navigate(['/login'])

        }
      },
        err => {
          if (err.statusText == "Conflict") {
            console.log("Conflict");
            this.validMessage = "Obecny login jest juz w bazie!"
          }

        }
      )
    } else {
      this.validMessage = "Login lub haslo za krótkie!"
    }
  }

}
