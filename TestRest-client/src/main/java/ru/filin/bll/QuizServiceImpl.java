package ru.filin.bll;

import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.filin.App;
import ru.filin.DTO.QuizDTO;
import ru.filin.QuizService;

import java.util.ArrayList;
import java.util.List;

public class QuizServiceImpl {
    private List<QuizDTO> quizDTOS = new ArrayList<>();

    private QuizService service;

    private App app;

    public QuizServiceImpl(QuizService service, App app) {
        this.service = service;
        this.app = app;
    }

    public List<QuizDTO> findAll() {
        service.findAll(new MethodCallback<List<QuizDTO>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                throw new RuntimeException();
            }

            @Override
            public void onSuccess(Method method, List<QuizDTO> response) {
                quizDTOS = response;
                app.refreshQuizList();
                app.refreshQuizList2();
            }
        });

        return quizDTOS;
    }

    public void addQuiz(QuizDTO quiz) {
        service.addQuiz(quiz, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                throw new RuntimeException();
            }

            @Override
            public void onSuccess(Method method, Void response) {
                findAll();
                app.refreshQuizList();
            }
        });
    }

    public List<QuizDTO> getQuizzes() {
        return new ArrayList<>(quizDTOS);
    }

    public void update(QuizDTO quiz) {
        service.updateQuiz(quiz, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("ddd");
                throw new RuntimeException();
            }

            @Override
            public void onSuccess(Method method, Void response) {
                findAll();
                app.refreshQuizList();
            }
        });
    }
}
