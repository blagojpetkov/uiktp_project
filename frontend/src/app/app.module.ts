import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from "./app-routing.module";
import { ProfileComponent } from './components/profile/profile.component';
import { HomeComponent } from './components/home/home.component';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { UsersService } from "./services/users.service";
import { CoursesService } from "./services/courses.service";

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
  ],
  providers: [
    UsersService,
    CoursesService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
