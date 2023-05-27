package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.models.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long courseId);
    void createCourse(Course course);
    void updateCourse(Long courseId, Course updatedCourse);
    void deleteCourse(Long courseId);
}
