import { User } from "./user";
import { Course } from "./course";

export class Enrollment {
  id: number;
  user: User;
  course: Course;
  enrollmentDate: Date;
}
