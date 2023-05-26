import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ProfileComponent } from "./components/profile/profile.component";
import { HomeComponent } from "./components/home/home.component";
import { CoursesComponent } from "./components/courses/courses.component";
import { LoginPageComponent } from "./components/login-page/login-page.component";
import { RegisterPageComponent } from "./components/register-page/register-page.component";
import { ProfilePublicComponent } from "./components/profile-public/profile-public.component";
import { CourseViewComponent } from "./components/course-view/course-view.component";
import { LectureViewComponent } from "./components/lecture-view/lecture-view.component";

const routes: Routes = [
  { path: 'profile', component: ProfileComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'view-profile', component: ProfilePublicComponent },
  { path: 'course-view', component: CourseViewComponent },
  { path: 'lecture', component: LectureViewComponent },
  { path: '**', component: HomeComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
