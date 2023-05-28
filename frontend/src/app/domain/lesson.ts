import { Course } from "./course";
import { Part } from "./part";

export class Lesson {
  id!: number;
  title!: string;
  description!: string;
  content!: string;
  number!: number;
  course!: Course;
  parts!: Part[];
}

