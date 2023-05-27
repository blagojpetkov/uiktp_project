import { Injectable } from "@angular/core";
import { Observable, firstValueFrom, of } from "rxjs";
import { User } from "../domain/user";
import { TEST_USER } from "../test-data";
import { Course } from "../domain/course";
import { TEST_COURSES } from "../test-data";
import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

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

  async updateUserInfo(currentUsername: string, newUsername:string, newFirstName:string, newLastName:string){
    await firstValueFrom(this.http.post(`/api/profile/edit?currentUsername=${currentUsername}&newUsername=${newUsername}&newFirstName=${newFirstName}&newLastName=${newLastName}`,''))
    this.router.navigate(['/profile']);
  }
}
