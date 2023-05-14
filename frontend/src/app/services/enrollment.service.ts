import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Course } from "../domain/course";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {

  constructor(private http: HttpClient) {
  }

  getCoursesForUser(): Observable<Course[]> {
    return this.http.get<Course[]>(`/api/enrollment/courses_for_user`)
  }
}
