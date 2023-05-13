import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { User } from "../domain/user";
import { TEST_USER } from "../test-data";
import { Course } from "../domain/course";
import { TEST_COURSES } from "../test-data";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  getUser(): Observable<User> {
    // TODO change to api when it is implemented
    return of(TEST_USER)
  }

  getUserByUsername(username: string): Observable<User> {
    // TODO: API REQUEST
    return of(TEST_USER)
  }

  getUserAchievements(username: string): Observable<Course[]> {
    // TODO: API REQUEST
    return of(TEST_COURSES);
  }
}
