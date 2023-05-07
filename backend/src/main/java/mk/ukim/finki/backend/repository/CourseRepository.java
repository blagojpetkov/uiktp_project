package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.category = :category")
    List<Course> coursesForCategory(String category);
}
