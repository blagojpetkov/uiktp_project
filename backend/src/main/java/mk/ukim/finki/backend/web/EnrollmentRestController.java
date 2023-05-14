package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.EnrollmentRepository;
import mk.ukim.finki.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentRestController {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    public EnrollmentRestController(
            EnrollmentRepository enrollmentRepository,
            UserRepository userRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/courses_for_user")
    public List<Course> mostPopularCourses(Authentication authentication) {
        User user = this.userRepository.findByUsername(authentication.getName()).orElseThrow();
        return this.enrollmentRepository.getCoursesForUser(user);
    }
}
