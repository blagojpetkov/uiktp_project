import { Component, OnInit } from '@angular/core';
import { UsersService } from "../../services/users.service";
import { CoursesService } from "../../services/courses.service";
import { User } from "../../domain/user";
import { Course } from "../../domain/course";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  courses: Course[] = [];

  constructor(private usersService: UsersService,
              private coursesService: CoursesService) {
  }

  ngOnInit(): void {
    this.usersService.getUser().subscribe((user) => {
      this.user = user;
    })

    this.coursesService.getCourses().subscribe((courses) => {
      this.courses = courses;
    })
  }

}
