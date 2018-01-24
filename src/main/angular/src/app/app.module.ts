import { BrowserModule } from '@angular/platform-browser';
import { NgModule, } from '@angular/core';
import { Routes,RouterModule  } from '@angular/router';

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
import { MatDialogModule } from '@angular/material/dialog';
import { UICarouselModule } from "ui-carousel";
import { WelcomePageComponent } from './site/welcome-page/welcome-page.component';
import { AddPhotoPageComponent } from './site/add-photo-page/add-photo-page.component';
import { GetPhotoPageComponent } from './site/get-photo-page/get-photo-page.component';
import { UserServiceService } from './services/user-service.service';
import { HttpModule } from '@angular/http';







const appRoutes: Routes = [
  {path: '', component:WelcomePageComponent },
  {path: 'home', component:WelcomePageComponent },
  {path: 'addPhoto', component:AddPhotoPageComponent},
  {path: 'getPhoto', component:GetPhotoPageComponent},
]



@NgModule({
  declarations: [
    AppComponent,
    DialogLoginComponent,
    DialogRegistrationComponent,
    HeaderComponent,
    WelcomePageComponent,
    AddPhotoPageComponent,
    GetPhotoPageComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
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
    UserServiceService
  ],
  entryComponents: [
    DialogLoginComponent,
    DialogRegistrationComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
