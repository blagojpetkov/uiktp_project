package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
