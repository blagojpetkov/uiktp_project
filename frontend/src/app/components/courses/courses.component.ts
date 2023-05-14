import { Component, OnInit } from '@angular/core';
import { CoursesService } from "../../services/courses.service";
import { Course } from "../../domain/course";
import { DomSanitizer } from "@angular/platform-browser";

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

  constructor(
    private coursesService: CoursesService,
    private sanitizer: DomSanitizer,
  ) {
  }

  ngOnInit(): void {
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

}
