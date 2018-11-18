package ru.filin.testSystem.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.filin.testSystem.Services.QuizService;
import ru.filin.testSystem.domain.Quiz;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizRestController {
    @Autowired
    QuizService quizService;

    @GetMapping
    public List<Quiz> getQuiz() {
        return  quizService.findAll();
    }

    @GetMapping("{id}")
    public Quiz getByID(@PathVariable long id) {
        return quizService.findByID(id);
    }

    @PostMapping
    public void addQuiz(@RequestParam String title) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quizService.add(quiz);
    }

}
