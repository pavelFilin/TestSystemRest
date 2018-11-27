package ru.filin.testSystem.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.filin.DTO.QuizDTO;
import ru.filin.testSystem.Services.QuizService;
import ru.filin.testSystem.domain.Quiz;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void addQuiz(@RequestBody Quiz quizDTO) {
        Quiz quiz1 = new Quiz();
        quiz1.setTitle(quizDTO.getTitle());
        quizService.add(quiz1);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public void putQuiz(@RequestBody Quiz quizDTO) {
        quizService.save(quizDTO);
    }


}
