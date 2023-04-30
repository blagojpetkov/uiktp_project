import { Component, OnInit } from '@angular/core';
import { CoursesService } from "../../services/courses.service";
import { Course } from "../../domain/course";

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  courses: Course[] = [];
  categories: string[] = ['Data Science', 'Web Development', 'Python', 'Drawing', 'Marketing', 'Music']
  activeCategory = this.categories[0];

  constructor(
    private coursesService: CoursesService
  ) {
  }

  ngOnInit(): void {
    this.coursesService.getCourses().subscribe(courses => this.courses = courses);
    // TODO make api call to get courses by category for the currently selected category which is this.categories[0]
  }

  onCategoryChange(category: string) {
    this.activeCategory = category;
    // TODO make an api call to get courses for the specific category
  }

}
