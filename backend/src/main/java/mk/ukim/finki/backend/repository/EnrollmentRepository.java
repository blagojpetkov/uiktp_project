package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Enrollment;
import mk.ukim.finki.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Transactional
    @Query("select e.course from Enrollment e where e.user = :user")
    List<Course> getCoursesForUser(User user);
}
