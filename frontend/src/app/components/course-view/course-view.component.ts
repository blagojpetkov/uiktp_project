import { Component, OnInit } from '@angular/core';
import { SharedDataService } from 'src/app/services/shared-data/shared-data.service';

@Component({
  selector: 'app-course-view',
  templateUrl: './course-view.component.html',
  styleUrls: ['./course-view.component.css']
})
export class CourseViewComponent implements OnInit {

  selectedCourse: any;
  lecture: any;
  course: any;
  lectures: any[] = [
    {
      name: 'Object 1',
      description: 'Description for Object 1'
    },
    {
      name: 'Object 2',
      description: 'Description for Object 2'
    },
    {
      name: 'Object 3',
      description: 'Description for Object 3'
    }
  ];

  constructor(private sharedDataService: SharedDataService) { }

  ngOnInit(): void {
    this.sharedDataService.courseData$.subscribe(data => {
      this.selectedCourse = data;
      console.log("This selected course");
      
      console.log(this.selectedCourse);
      
    });

  }

  
}


