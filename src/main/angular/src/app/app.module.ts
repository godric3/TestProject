import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { JwtInterceptor } from './helpers/jwt-interceptor';
import { DialogLoginComponent } from './dialog-login/dialog-login.component';
import { DialogRegistrationComponent } from './dialog-registration/dialog-registration.component';
import { HeaderComponent } from './header/header.component';
import {MatDialogModule} from '@angular/material/dialog';
import { UICarouselModule } from "ui-carousel";










@NgModule({
  declarations: [
    AppComponent,
    DialogLoginComponent,
    DialogRegistrationComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatFormFieldModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    UICarouselModule

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
  },
  ],
  entryComponents:[
    DialogLoginComponent,
    DialogRegistrationComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
