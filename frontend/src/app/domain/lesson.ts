import { Course } from "./course";

export interface Lesson {
  id: number;
  title: string;
  description: string;
  content: string;
  number: number;
  course: Course;
}
