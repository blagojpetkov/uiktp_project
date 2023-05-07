package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.repository.CourseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesRestController {

    private final CourseRepository courseRepository;

    public CoursesRestController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> coursesForCategory(@RequestParam String category) {
        return this.courseRepository.coursesForCategory(category);
    }
}
