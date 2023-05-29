package mk.ukim.finki.backend.service.impl;

import mk.ukim.finki.backend.models.Review;
import mk.ukim.finki.backend.models.Test;
import mk.ukim.finki.backend.repository.TestRepository;
import mk.ukim.finki.backend.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test findById(Long id) {
        return this.testRepository.findById(id).orElseThrow(() -> new RuntimeException("Test with id " + id + " not found"));
    }

    public List<Test> findAll() {
        return this.testRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public void save(Test test) {
        testRepository.save(test);
    }
}
