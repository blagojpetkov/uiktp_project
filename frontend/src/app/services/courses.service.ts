import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { Course } from "../domain/course";
import { TEST_COURSES } from "../test-data";

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  getCourses(): Observable<Course[]> {
    // TODO return from api when it is created
    return of(TEST_COURSES);
  }

}
