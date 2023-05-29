package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserId(Long id);
    List<ShoppingCart> findByCoursesContaining(Course course);
}
