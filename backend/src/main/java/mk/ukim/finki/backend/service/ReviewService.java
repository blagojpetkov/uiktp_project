package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Review;
import mk.ukim.finki.backend.models.User;

import java.util.Date;
import java.util.List;

public interface ReviewService {

    Review findById(Long id);

    List<Review> findAll();

    Review create(Long userId, Long courseId, int grade, String text, Date reviewDate);

    Review update(Long id, Long userId, Long courseId, int grade, String text, Date reviewDate);

    void delete(Long id);
}
