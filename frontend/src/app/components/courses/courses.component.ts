import { Component, OnInit } from '@angular/core';
import { CoursesService } from "../../services/courses.service";
import { DomSanitizer } from "@angular/platform-browser";
import { Course } from 'src/app/domain/course';
import { Router } from '@angular/router';
import { SharedDataService } from 'src/app/services/shared-data/shared-data.service';
import { Enrollment } from 'src/app/domain/enrollment';
import { User } from 'src/app/domain/user';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  courses: Course[] = [];
  categories: string[] = ['Data Science', 'Web Development', 'Python', 'Drawing', 'Marketing', 'Music', 'Mobile Development']
  activeCategory = this.categories[0];
  coursesByCategory: Course[] = [];
  course: any;
  course2: any;

  sharedData1: any;
  sharedData2: any;

  constructor(
    private coursesService: CoursesService,
    private sanitizer: DomSanitizer,
    private router: Router,
    private sharedDataService: SharedDataService
  ) {
  }

  ngOnInit(): void {
    this.sharedDataService.data$.subscribe(data => {
      debugger
      this.sharedData1 = data;
    });

    this.sharedDataService.data2$.subscribe(data => {
      debugger
      this.sharedData2 = data;
    });

    this.coursesService.getCourses().subscribe(mostPopularCourses => this.courses = mostPopularCourses.slice(0, 5))
    this.coursesService.getCoursesByCategory(this.activeCategory).subscribe(result => {
      this.coursesByCategory = result;
    })
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

  onCourseClicked(courseSelected: any) {
    this.sharedDataService.setCourseData(courseSelected);
    // this.sharedDataService.setCourseData(this.course);
    this.router.navigate(['/course-view']);
  }

  onAddToShoppingCartClick(course: Course) {
    // implementation

    // console.log(this.coursesService.addToShoppingCart(course.id));
    // debugger
    // console.log(this.coursesService.addToShoppingCart(course.id,this.sharedDataService.password,this.sharedDataService.username));
    // this.coursesService.addToShoppingCart(course.id,this.sharedDataService.password,this.sharedDataService.username);
    // debugger
    console.log("shared data:");

    console.log(this.sharedData1);

    console.log(this.sharedData2);
    // Target component
    // const sharedData = JSON.parse(sessionStorage.getItem('sharedData'));

    // Target component
    const sharedDataString = sessionStorage.getItem('sharedData');
    const sharedData = sharedDataString ? JSON.parse(sharedDataString) : null;

    const sharedDataString2 = sessionStorage.getItem('sharedData2');
    const sharedData2 = sharedDataString2 ? JSON.parse(sharedDataString2) : null;


    console.log("SHARED DATA");
    console.log(sharedData);
    
    
    // console.log("clicked", course.name)
    this.coursesService.addToShoppingCart(course.id, sharedData, sharedData2).subscribe(data =>{
      console.log("DATA FROM API");
      
      console.log(data);
      if(data){
        // this.router.navigate(['/course-view']);
      // After a successful operation
      window.location.href = 'http://localhost:8080/courses/shopping_cart';

      }
    }

    
      // () => {
      //   console.log('Course added to shopping cart successfully');
      //   // Perform any additional actions after successful addition
      // },
      // (error) => {
      //   console.error('Error adding course to shopping cart:', error);
      //   // Handle error case
      // }
    );
  }
}
