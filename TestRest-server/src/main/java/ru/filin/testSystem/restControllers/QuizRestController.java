package ru.filin.testSystem.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.filin.testSystem.Services.QuizService;
import ru.filin.testSystem.domain.Quiz;

import java.util.List;

@RestController
public class QuizRestController {
    @Autowired
    QuizService quizService;

    @GetMapping("/quiz")
    public List<Quiz> getQuiz() {
        return  quizService.findAll();
    }

}
