import { Component, OnInit } from '@angular/core';
import { DialogRegistrationComponent } from '../dialog-registration/dialog-registration.component';
import { MatDialog } from '@angular/material/dialog';
import { DialogLoginComponent } from '../dialog-login/dialog-login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  openRegistrationDialog() {
    let dialogRef = this.dialog.open(DialogRegistrationComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The registration dialog was closed');
    });
  }

  openLoginDialog() {
    let dialogRef = this.dialog.open(DialogLoginComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The login dialog was closed');
    });
  }
}
