package ru.filin.testSystem.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public List<ru.filin.testSystem.domain.Quiz> getQuiz() {
        return  quizService.findAll();
    }

    @GetMapping("{id}")
    public ru.filin.testSystem.domain.Quiz getByID(@PathVariable long id) {
        return quizService.findByID(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void addQuiz(@RequestBody ru.filin.testSystem.domain.Quiz quiz) {
        ru.filin.testSystem.domain.Quiz quiz1 = new ru.filin.testSystem.domain.Quiz();
        quiz1.setTitle(quiz.getTitle());
        quizService.add(quiz1);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public void putQuiz(@RequestBody Quiz quiz) {
        System.out.println(quiz);
//        quizService.save(quiz);
    }

//    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @PutMapping
    public void putQuiz(@RequestBody Map<String, String> quizDTO) {
//        quizService.save(quizDTO);
        System.out.println(quizDTO);
    }


}
