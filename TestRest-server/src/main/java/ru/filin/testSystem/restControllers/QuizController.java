package ru.filin.testSystem.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.filin.testSystem.domain.Quiz;
import ru.filin.testSystem.repositories.QuizRepository;

@RestController
public class QuizController {
//    @Autowired
//    QuizRepository quizRepository;

    @GetMapping("/quiz")
    public Quiz getQuiz() {
//        return quizRepository.findByTitle("1");
        return new Quiz();
    }
}
