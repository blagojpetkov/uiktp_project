package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.models.Lesson;
import mk.ukim.finki.backend.models.Question;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionService {
    Question findById(Long id);

    List<Question> findAll();

    void delete(Long id);

    void save(Question question);
}
