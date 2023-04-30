import { User } from "./domain/user";
import { Role } from "./domain/role";
import { Course } from "./domain/course";

export const TEST_USER: User = { id: 1, username: 'test', firstName: 'test', lastName: 'test', role: Role.ROLE_ADMIN };

export const TEST_COURSES: Course[] = [
  { id: 1, name: 'Course 1', description: 'Description 1', instructor: TEST_USER, category: 'Category 1', image: new Blob(), enrollments: [], lessons: [] },
  { id: 2, name: 'Course 2', description: 'Description 2', instructor: TEST_USER, category: 'Category 2', image: new Blob(), enrollments: [], lessons: [] },
  { id: 3, name: 'Course 3', description: 'Description 3', instructor: TEST_USER, category: 'Category 3', image: new Blob(), enrollments: [], lessons: [] },
  { id: 4, name: 'Course 4', description: 'Description 4', instructor: TEST_USER, category: 'Category 4', image: new Blob(), enrollments: [], lessons: [] },
];
