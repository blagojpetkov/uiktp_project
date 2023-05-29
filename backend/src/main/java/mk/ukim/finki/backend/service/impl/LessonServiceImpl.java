package mk.ukim.finki.backend.service.impl;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Lesson;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.repository.LessonRepository;
import mk.ukim.finki.backend.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonServiceImpl(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Lesson findById(Long id) {
        return this.lessonRepository.findById(id).orElseThrow(() -> new RuntimeException("Lesson with id " + id + " not found"));
    }

    @Override
    public List<Lesson> findAll() {
        return this.lessonRepository.findAll();
    }

    @Override
    public Lesson create(String title, String description, String content, int number, Long courseId, MultipartFile video) {
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        return this.lessonRepository.save(new Lesson(title, description, content, number, course, video));
    }

    @Override
    public Lesson update(Long id, String title, String description, String content, int number, Long courseId) {
        Lesson lesson = this.findById(id);

        lesson.setTitle(title);
        lesson.setDescription(description);
        lesson.setContent(content);
        lesson.setNumber(number);

        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        lesson.setCourse(course);

        return this.lessonRepository.save(lesson);
    }

    @Override
    public void delete(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        Course course = lesson.getCourse();
        course.getLessons().remove(lesson);
        courseRepository.save(course);
    }

    @Override
    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }
}
