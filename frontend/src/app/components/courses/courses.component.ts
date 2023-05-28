import { Component, OnInit } from '@angular/core';
import { CoursesService } from "../../services/courses.service";
import { DomSanitizer } from "@angular/platform-browser";
import { Course } from 'src/app/domain/course';
import { Router } from '@angular/router';
import { SharedDataService } from 'src/app/services/shared-data/shared-data.service';

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

  constructor(
    private coursesService: CoursesService,
    private sanitizer: DomSanitizer,
    private router:Router,
    private sharedDataService:SharedDataService
  ) {
  }

  ngOnInit(): void {
    this.coursesService.getCourses().subscribe(mostPopularCourses => this.courses = mostPopularCourses.slice(0, 5))
    this.coursesService.getCoursesByCategory(this.activeCategory).subscribe(result => {
      this.coursesByCategory = result;
    });
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
    this.sharedDataService.setCourseData(courseSelected);
    this.router.navigate(['/course-view']);
  }
}
