import { Course } from "./course";

export class Lesson {
  id: number;
  title: string;
  description: string;
  content: string;
  number: number;
  course: Course;
}
