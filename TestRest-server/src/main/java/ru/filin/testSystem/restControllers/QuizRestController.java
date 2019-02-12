package ru.filin.testSystem.restControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.filin.testSystem.Services.QuizService;
import ru.filin.testSystem.domain.Quiz;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizRestController {
    //    final static Logger logger = Logger.getLogger(QuizRestController.class);
    @Autowired
    QuizService quizService;

    @GetMapping
    public List<ru.filin.testSystem.domain.Quiz> getQuiz() throws InterruptedException {
        Thread.sleep(1000);
//        logger.warn("This is warn : " );
//        logger.error("This is error : "  );
//        logger.fatal("This is fatal : " );
//        logger.debug("sdsd");

        List<Quiz> all = quizService.findAll();
        return all;
    }

    @GetMapping("{id}")
    public ru.filin.testSystem.domain.Quiz getByID(@PathVariable long id) {
        return quizService.findByID(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void addQuiz(@RequestBody Quiz quiz) {
        quizService.add(quiz);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public void putQuiz(@RequestBody Quiz quiz) {
        quiz.getQuestionFreeTexts().forEach(q -> q.setQuiz(quiz));
        quiz.getQuestionGroups().forEach(q -> q.setQuiz(quiz));
        quiz.getQuestionStandard().forEach(q -> q.setQuiz(quiz));

        quiz.getQuestionFreeTexts().forEach(x -> x.getAnswerFreeText().forEach(q -> q.setQuestionFreeText(x)));
        quiz.getQuestionStandard().forEach(x -> x.getAnswerStandards().forEach(q -> q.setQuestionStandard(x)));

        //todo saving groups answers
//        quiz.getQuestionGroups().forEach(x -> x.get().forEach(q -> q.setQuestionFreeText(x)));
        quizService.save(quiz);
    }

}
