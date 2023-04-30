import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from "./app-routing.module";
import { ProfileComponent } from './components/profile/profile.component';
import { HomeComponent } from './components/home/home.component';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { UsersService } from "./services/users.service";
import { CoursesService } from "./services/courses.service";
import { HttpClientModule } from "@angular/common/http";
import { CoursesComponent } from './components/courses/courses.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    HomeComponent,
    CoursesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
  ],
  providers: [
    UsersService,
    CoursesService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
