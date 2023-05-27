package mk.ukim.finki.backend.service.impl;

import mk.ukim.finki.backend.models.Review;
import mk.ukim.finki.backend.models.Test;
import mk.ukim.finki.backend.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl {

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
}
