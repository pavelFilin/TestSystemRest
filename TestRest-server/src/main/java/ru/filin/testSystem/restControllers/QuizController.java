package ru.filin.testSystem.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.filin.testSystem.domain.Quiz;
import ru.filin.testSystem.repositories.QuizRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {
    @Autowired
    QuizRepository quizRepository;

    @GetMapping("/quiz")
    public List<Quiz> getQuiz() {
        return  quizRepository.findAll();
    }
}
