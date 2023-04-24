package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
