import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Course } from "../domain/course";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(private http: HttpClient) {
  }

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`/api/courses`)
  }

  getCoursesByCategory(category: string): Observable<Course[]> {
    return this.http.get<Course[]>(`/api/courses?category=${category}`)
  }

  // addToShoppingCart(courseId: number,username:any,password:any): Observable<any> {
  //   debugger
  //   // const url = `localhost:8080/courses/add_to_shopping_cart/${courseId}`;
  //   const headers = new HttpHeaders()
  //     .set('courseId', courseId.toString())
  //     .set('username', username)
  //     .set('password', password);
  //   const baseUrl = 'http://localhost:8080/courses/add_to_shopping_cart'
  //   // return this.http.get<any>(url);
  //   return this.http.get<any>(baseUrl, { headers });
  // }
  addToShoppingCart(courseId: number, username: string, password: string) {
    const baseUrl = 'http://localhost:8080/courses/add_to_shopping_cart'
    // const headers = new HttpHeaders()
      // .set('courseId', courseId.toString())
      // .set('username', username)
      // .set('password', password);
      const params = new HttpParams()
      .set('courseId', courseId.toString())
      .set('username', username)
      .set('password', password);
    console.log("Before api call");
    
    return this.http.get<any>(baseUrl, { params });
  }
}
