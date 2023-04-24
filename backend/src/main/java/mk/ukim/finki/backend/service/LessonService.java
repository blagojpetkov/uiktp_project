package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Lesson;

import java.util.Date;
import java.util.List;

public interface LessonService {

    Lesson findById(Long id);

    List<Lesson> findAll();

    Lesson create(String title, String description, String content, int number, Long courseId);

    Lesson update(Long id, String title, String description, String content, int number, Long courseId);

    void delete(Long id);
}
