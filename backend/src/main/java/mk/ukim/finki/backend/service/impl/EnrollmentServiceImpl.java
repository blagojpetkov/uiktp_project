package mk.ukim.finki.backend.service.impl;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Enrollment;
import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.repository.EnrollmentRepository;
import mk.ukim.finki.backend.repository.UserRepository;
import mk.ukim.finki.backend.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Enrollment findById(Long id) {
        return this.enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Review with id " + id + " not found"));
    }

    @Override
    public List<Enrollment> findAll() {
        return this.enrollmentRepository.findAll();
    }

    @Override
    public Enrollment create(Long userId, Long courseId, Date enrollmentDate) {
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));
        return this.enrollmentRepository.save(new Enrollment(user, course, enrollmentDate));
    }

    @Override
    public Enrollment update(Long id, Long userId, Long courseId, Date enrollmentDate) {
        Enrollment enrollment = this.findById(id);

        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));

        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(enrollmentDate);

        return this.enrollmentRepository.save(enrollment);
    }

    @Override
    public void delete(Long id) {
        this.enrollmentRepository.deleteById(id);
    }
}
