package ru.filin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.filin.DTO.QuestionFreeText;
import ru.filin.DTO.QuestionStandard;
import ru.filin.DTO.QuestionType;
import ru.filin.DTO.Quiz;
import ru.filin.bll.QuizServiceImpl;
import ru.filin.bll.widgets.AdminQuizPanel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    private final QuizServiceImpl quizService = new QuizServiceImpl(GWT.create(QuizService.class), this);

    private final TextBox editQuizTitleTextBox = new TextBox();

    private final Button addNewQuestionButton = new Button("Add question");

    private final VerticalPanel quizList = new VerticalPanel();
    private final HorizontalPanel superQuizPanel = new HorizontalPanel();
    VerticalPanel questionPanel = new VerticalPanel();

    AdminQuizPanel adminQuizPanel = new AdminQuizPanel("admin-quiz-list");

    @Override
    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get("admin-quizzes-container");

        rootPanel.add(adminQuizPanel);

        quizService.findAll();

        Button addQuizButton = new Button("Create quiz");
        TextBox quizTitleTextBox = new TextBox();

        addQuizButton.addClickHandler(event -> {
            Quiz quiz = new Quiz();
            quiz.setTitle(quizTitleTextBox.getValue());
            quizService.addQuiz(quiz);
            adminQuizPanel.refresh(quizService);
        });


        rootPanel.add(addQuizButton);
        rootPanel.add(quizTitleTextBox);
    }

    public void refreshAdminPanel() {
        adminQuizPanel.refresh(quizService);
    }
}
