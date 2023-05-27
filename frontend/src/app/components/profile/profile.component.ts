import { Component, OnInit } from '@angular/core';
import { UsersService } from "../../services/users.service";
import { User } from "../../domain/user";
import { Course } from "../../domain/course";
import { EnrollmentService } from "../../services/enrollment.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  courses: Course[] = [];
  public editView:boolean = false;
  name: string = '';
  surname: string = '';
  username: string = '';
 

  constructor(private usersService: UsersService,
              private enrollmentService: EnrollmentService,) {
  }

  ngOnInit(): void {
    this.usersService.getUser().subscribe((user) => this.user = user)
    this.enrollmentService.getCoursesForUser().subscribe(result => this.courses = result)
  }

  public onSubmit(username:string, name:string, surname:string) {
    console.log(this.user.username, username, name, surname);
    // WITHOUT API CALL ( could be used for presentation ):
    // this.user.username= username;
    // this.user.firstName= name;
    // this.user.lastName= surname;
    // this.editView=false;
    
    this.usersService.updateUserInfo(this.user.username, username,name,surname)
  }

  public toggleEdit(){
    if(this.editView==false){
      this.editView=true;
    }
    else{
      this.editView=false;
    }
  }

}
