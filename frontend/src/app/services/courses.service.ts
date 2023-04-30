import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { Course } from "../domain/course";
import { TEST_COURSES } from "../test-data";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(private http: HttpClient) {
  }

  getCourses(): Observable<Course[]> {
    // TODO return from api when it is created
    return of(TEST_COURSES);
  }

  getCoursesByCategory(category: string): Observable<Course[]> {
    return this.http.get<Course[]>(`/api/courses?category=${category}`)
  }

}
