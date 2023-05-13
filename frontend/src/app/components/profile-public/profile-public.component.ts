import { Component, OnInit } from '@angular/core';
import { UsersService } from "../../services/users.service";
import { CoursesService } from "../../services/courses.service";
import { User } from "../../domain/user";
import { Course } from "../../domain/course";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-public-profile',
  templateUrl: './profile-public.component.html',
  styleUrls: ['./profile-public.component.css']
})
export class ProfilePublicComponent implements OnInit {

  user: User; 
  achievements: Course[] = [];
  username: string;

  constructor(private usersService: UsersService,
              private route: ActivatedRoute,
              private router:Router,
              private coursesService: CoursesService) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
                           console.log(params);
                           this.username = params['username'];
                           console.log(this.username); 
                          }
                        );

    this.usersService.getUserByUsername(this.username).subscribe((user) => {
      this.user = user;
    })

    this.usersService.getUserAchievements(this.username).subscribe((achievements) => {
      this.achievements = achievements;
    })
  }

}
