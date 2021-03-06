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
import { HeaderComponent } from './header/header.component';
import { MatDialogModule } from '@angular/material/dialog';
import { UICarouselModule } from "ui-carousel";
import { WelcomePageComponent } from './site/welcome-page/welcome-page.component';
import { AddPhotoPageComponent } from './site/add-photo-page/add-photo-page.component';
import { GetPhotoPageComponent } from './site/get-photo-page/get-photo-page.component';
import { UserService } from './services/user-service.service';
import { HttpModule } from '@angular/http';
import { LoginPageComponent } from './site/login-page/login-page.component';
import { RegisterPageComponent } from './site/register-page/register-page.component';
import { FormsModule } from '@angular/forms';
import {MatListModule} from '@angular/material/list';
import { ImageService } from './services/image.service';
import {MatSelectModule} from '@angular/material/select';
import { FullSizeImageDialogComponent } from './site/get-photo-page/full-size-image-dialog/full-size-image-dialog.component';
import { AddingPhotoResultDialogComponent } from './site/add-photo-page/adding-photo-result-dialog/adding-photo-result-dialog.component';
import { MyPhotosPageComponent } from './site/my-photos-page/my-photos-page.component';









const appRoutes: Routes = [
  {path: '', component:WelcomePageComponent },
  {path: 'home', component:WelcomePageComponent },
  {path: 'myPhoto',component:MyPhotosPageComponent},
  {path: 'addPhoto', component:AddPhotoPageComponent},
  {path: 'getPhoto', component:GetPhotoPageComponent},
  {path: 'login', component:LoginPageComponent},
  {path: 'register', component:RegisterPageComponent},
]



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    WelcomePageComponent,
    AddPhotoPageComponent,
    GetPhotoPageComponent,
    LoginPageComponent,
    RegisterPageComponent,
    FullSizeImageDialogComponent,
    AddingPhotoResultDialogComponent,
    MyPhotosPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
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
    UICarouselModule,
    MatListModule,
    MatSelectModule,
    HttpClientModule

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    UserService,
    ImageService
  ],
  entryComponents: [
    FullSizeImageDialogComponent,
    AddingPhotoResultDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
