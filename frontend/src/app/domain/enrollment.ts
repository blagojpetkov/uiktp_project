import { User } from "./user";
import { Course } from "./course";

export interface Enrollment {
  id: number;
  user: User;
  course: Course;
  enrollmentDate: Date;
}
