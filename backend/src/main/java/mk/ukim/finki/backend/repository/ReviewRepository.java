package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
