package mk.ukim.finki.backend.service.impl;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Question;
import mk.ukim.finki.backend.models.Review;
import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.repository.QuestionRepository;
import mk.ukim.finki.backend.repository.ReviewRepository;
import mk.ukim.finki.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl {

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


    public Question create(String text, String answer1, String answer2, String answer3, String answer4, int correctAnswer) {
        return this.questionRepository.save(new Question(text, answer1, answer2, answer3, answer4, correctAnswer));
    }


    public void delete(Long id) {
        this.questionRepository.deleteById(id);
    }
}
