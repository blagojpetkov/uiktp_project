import { User } from "./user";
import { Course } from "./course";

export interface Review {
  id: number;
  user: User;
  course: Course;
  grade: number;
  text: string;
  reviewDate: Date;
}
