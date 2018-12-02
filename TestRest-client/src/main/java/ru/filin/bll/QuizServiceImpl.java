package ru.filin.bll;

import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.filin.App;
import ru.filin.DTO.Quiz;
import ru.filin.QuizService;

import java.util.ArrayList;
import java.util.List;

public class QuizServiceImpl {
    private List<Quiz> quizzes = new ArrayList<>();

    private QuizService service;

    private App app;

    public QuizServiceImpl(QuizService service, App app) {
        this.service = service;
        this.app = app;
    }

    public List<Quiz> findAll() {
        service.findAll(new MethodCallback<List<Quiz>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                throw new RuntimeException();
            }

            @Override
            public void onSuccess(Method method, List<Quiz> response) {
                quizzes = response;
                app.refreshQuizList();
            }
        });

        return quizzes;
    }

    public void addQuiz(Quiz quiz) {
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

    public List<Quiz> getQuizzes() {
        return new ArrayList<>(quizzes);
    }

    public void update(Quiz quiz) {
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
