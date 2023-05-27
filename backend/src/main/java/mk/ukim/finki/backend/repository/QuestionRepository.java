package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
