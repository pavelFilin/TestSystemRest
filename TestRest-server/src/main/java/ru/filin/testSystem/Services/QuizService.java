package ru.filin.testSystem.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.filin.testSystem.domain.Quiz;
import ru.filin.testSystem.repositories.QuizRepository;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Quiz findByID(long id) {
        return quizRepository.findOne(id);
    }

    public List<Quiz> findByTitle(String title) {
        return quizRepository.findByTitle(title);
    }

    public void add(Quiz quiz) {
        quizRepository.save(quiz);
    }

}
