import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  private courseDataSubject = new BehaviorSubject<any>(null);
  public courseData$ = this.courseDataSubject.asObservable();

  constructor() { }

  setCourseData(courseData: any) {
    this.courseDataSubject.next(courseData);
  }
}
