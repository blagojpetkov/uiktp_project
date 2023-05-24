package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Transactional
    @Query("select c from Course c where c.category = :category")
    List<Course> coursesForCategory(String category);

    @Transactional
    List<Course> findCoursesByInstructorUsername(String username);
}
