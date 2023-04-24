package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Enrollment;
import mk.ukim.finki.backend.models.Lesson;
import mk.ukim.finki.backend.models.User;

import java.util.Date;
import java.util.List;

public interface EnrollmentService {

    Enrollment findById(Long id);

    List<Enrollment> findAll();

    Enrollment create(Long userId, Long courseId, Date enrollmentDate);

    Enrollment update(Long id, Long userId, Long courseId, Date enrollmentDate);

    void delete(Long id);

}
