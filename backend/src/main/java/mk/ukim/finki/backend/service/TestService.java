package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.models.Lesson;
import mk.ukim.finki.backend.models.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestService {
    Test findById(Long id);

    List<Test> findAll();

    void delete(Long id);

    void save(Test test);
}
