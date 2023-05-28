import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from "./app-routing.module";
import { ProfileComponent } from './components/profile/profile.component';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { UsersService } from "./services/users.service";
import { CoursesService } from "./services/courses.service";
import { HttpClientModule } from "@angular/common/http";
import { CoursesComponent } from './components/courses/courses.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthenticationService } from "./services/authentication.service";
import { ProfilePublicComponent } from './components/profile-public/profile-public.component';
import { EnrollmentService } from "./services/enrollment.service";
import { CourseViewComponent } from './components/course-view/course-view.component';
import { LectureViewComponent } from './components/lecture-view/lecture-view.component';
import { NavbarComponent } from './components/navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    CoursesComponent,
    LoginPageComponent,
    RegisterPageComponent,
    ProfilePublicComponent,
    CourseViewComponent,
    LectureViewComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    UsersService,
    CoursesService,
    AuthenticationService,
    EnrollmentService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
