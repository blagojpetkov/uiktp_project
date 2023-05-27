package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
