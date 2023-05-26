import { Component, OnInit } from '@angular/core';
import { Lesson } from 'src/app/domain/lesson';
import { User } from 'src/app/domain/user';

@Component({
  selector: 'app-lecture-view',
  templateUrl: './lecture-view.component.html',
  styleUrls: ['./lecture-view.component.css']
})
export class LectureViewComponent implements OnInit {
  lessons:any[]=[];
  lesson: Lesson;
  constructor() { }

  ngOnInit(): void {
    this.lesson = {
      id: 1,
      title: 'Lesson Title',
      description: 'Lesson Description',
      content: 'Lesson Content',
      number: 1,
      course: {
        id: 1,
        name: 'Course Name',
        description: 'Course Description',
        instructor: new User,
        enrollments: [],
        category: 'Course Category',
        lessons: [],
        image: new Blob()
      }
    };

    debugger
    this.lessons.push(this.lesson);
  }
}


