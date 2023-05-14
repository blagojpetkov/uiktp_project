import { Component, OnInit } from '@angular/core';
import { UsersService } from "../../services/users.service";
import { User } from "../../domain/user";
import { Course } from "../../domain/course";
import { EnrollmentService } from "../../services/enrollment.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  courses: Course[] = [];

  constructor(private usersService: UsersService,
              private enrollmentService: EnrollmentService,) {
  }

  ngOnInit(): void {
    this.usersService.getUser().subscribe((user) => this.user = user)
    this.enrollmentService.getCoursesForUser().subscribe(result => this.courses = result)
  }

}
