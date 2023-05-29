package mk.ukim.finki.backend.service.impl;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Review;
import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.repository.ReviewRepository;
import mk.ukim.finki.backend.repository.UserRepository;
import mk.ukim.finki.backend.service.ReviewService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review with id " + id + " not found"));
    }

    @Override
    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Review create(Long userId, Long courseId, int grade, String text, Date reviewDate) {
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));
        return this.reviewRepository.save(new Review(user, course, grade, text, reviewDate));
    }

    @Override
    public Review update(Long id, Long userId, Long courseId, int grade, String text, Date reviewDate) {
        Review review = this.findById(id);

        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));

        review.setUser(user);
        review.setCourse(course);
        review.setGrade(grade);
        review.setText(text);
        review.setReviewDate(reviewDate);

        return this.reviewRepository.save(review);
    }

    @Override
    public void delete(Long id) {
        this.reviewRepository.deleteById(id);
    }

    @Override
    public Double averageReviewByCourse(Long courseId) {
        Course course = this.courseRepository.findById(courseId).orElseThrow();
        List<Review> reviewList = this.reviewRepository.findAll()
                .stream()
                .filter(review -> review.getCourse().equals(course))
                .collect(Collectors.toList());

        double average = reviewList.stream()
                .mapToDouble(Review::getGrade)
                .average()
                .orElse(0.0);
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(average));
    }

    @Override
    public List<Review> reviewsByCourse(Long courseId) {
        return this.reviewRepository.findAll()
                .stream()
                .filter(review -> review.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());
    }
}
