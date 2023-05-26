import { User } from "./user";
import { Enrollment } from "./enrollment";
import { Lesson } from "./lesson";

export class Course {
  id: number;
  name: string;
  description: string;
  instructor: User;
  enrollments: Enrollment[];
  category: string;
  lessons: Lesson[];
  image: Blob;
}
