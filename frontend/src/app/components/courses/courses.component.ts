import { Component, OnInit } from '@angular/core';
import { CoursesService } from "../../services/courses.service";
// import { Course } from "../../domain/course";
import { DomSanitizer } from "@angular/platform-browser";
import { Course } from 'src/app/domain/course';
import { Enrollment } from 'src/app/domain/enrollment';
import { User } from 'src/app/domain/user';
import { Router } from '@angular/router';
import { SharedDataService } from 'src/app/services/shared-data/shared-data.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  courses: Course[] = [];
  categories: string[] = ['Data Science', 'Web Development', 'Python', 'Drawing', 'Marketing', 'Music']
  activeCategory = this.categories[0];
  coursesByCategory: Course[] = [];

  course:any;
  course2:any;

  constructor(
    private coursesService: CoursesService,
    private sanitizer: DomSanitizer,
    private router:Router,
    private sharedDataService:SharedDataService
  ) {
  }

  ngOnInit(): void {
    this.course = new Course();
    this.coursesService.getCourses().subscribe(mostPopularCourses => this.courses = mostPopularCourses.slice(0, 5))
    this.coursesService.getCoursesByCategory(this.activeCategory).subscribe(result => {
      this.coursesByCategory = result;
      console.log(result);
      
    })
    this.setUpCourse();
    
  }

  setUpCourse(){
    debugger
    this.course.name="Data Science Course"
    // this.course.
    this.course.category=this.categories[0];
    this.course.description="Description for course";
    const enrollmentTemp = new Enrollment;
    enrollmentTemp.course = this.course;
    enrollmentTemp.enrollmentDate=new Date(2023,5,19);
    enrollmentTemp.id=0
    enrollmentTemp.user=new User;
    const enrollments = [];
    enrollments.push(enrollmentTemp);

    this.course.image= new Blob();
    this.course.enrollments=enrollments;
    this.course2 = new Course();
this.course2.name = "Data Science Course 2";
this.course2.category = this.categories[0];
this.course2.description = "Description for course 2";
const enrollmentTemp2 = new Enrollment();
enrollmentTemp.course = this.course2;
enrollmentTemp.enrollmentDate = new Date(2023, 5, 19);
enrollmentTemp.id = 0;
enrollmentTemp.user = new User();
const enrollments2 = [];
enrollments.push(enrollmentTemp);

this.course2.image = new Blob();
this.course2.enrollments = enrollments;
    this.courses.push(this.course2)
    this.courses.push(this.course)
    

  }
  onCategoryChange(category: string) {
    this.activeCategory = category;
    this.coursesService.getCoursesByCategory(this.activeCategory).subscribe(result => {
      this.coursesByCategory = result;
    })
  }

  previewImage(image: Blob) {
    const objectUrl = 'data:image/jpeg;base64,' + image;
    return this.sanitizer.bypassSecurityTrustUrl(objectUrl);
  }

  onCourseClicked(courseSelected:any){
    console.log("clicked");
    
    console.log(courseSelected);
    
    this.sharedDataService.setCourseData(courseSelected);
    this.router.navigate(['/course-view']);
  }
}
