import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  private courseDataSubject = new BehaviorSubject<any>(null);
  public courseData$ = this.courseDataSubject.asObservable();


  private dataSubject = new BehaviorSubject<any>(null);
  public data$ = this.dataSubject.asObservable();


  private dataSubject2 = new BehaviorSubject<any>(null);
  public data2$ = this.dataSubject.asObservable();
  // public username:any;
  // public password:any;

  constructor() { }

  setCourseData(courseData: any) {
    this.courseDataSubject.next(courseData);
  }

  setData(data: any,data2:any) {
    debugger
    this.dataSubject.next(data);
    this.dataSubject2.next(data2);
  }
}
