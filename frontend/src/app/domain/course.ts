import { User } from "./user";
import { Enrollment } from "./enrollment";
import { Lesson } from "./lesson";

export class Course {
  id!: number; // Add the ! symbol to indicate that it is required
  name!: string;
  description!: string;
  instructor!: User;
  enrollments!: Enrollment[];
  category!: string;
  lessons!: Lesson[];
  image!: Blob;
}
