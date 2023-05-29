package mk.ukim.finki.backend.service.impl;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Question;
import mk.ukim.finki.backend.models.Review;
import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.repository.QuestionRepository;
import mk.ukim.finki.backend.repository.ReviewRepository;
import mk.ukim.finki.backend.repository.UserRepository;
import mk.ukim.finki.backend.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Question findById(Long id) {
        return this.questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question with id " + id + " not found"));
    }


    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }



    public void delete(Long id) {
        this.questionRepository.deleteById(id);
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }
}
