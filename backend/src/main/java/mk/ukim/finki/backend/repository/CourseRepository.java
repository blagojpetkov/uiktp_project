package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
