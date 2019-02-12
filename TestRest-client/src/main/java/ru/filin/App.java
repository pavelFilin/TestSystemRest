package ru.filin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import ru.filin.DTO.Quiz;
import ru.filin.bll.QuizServiceImpl;
import ru.filin.bll.utils.MyProfiler;
import ru.filin.bll.widgets.AdminQuizPanel;
import ru.filin.bll.widgets.FlowQuizPanel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    private final QuizServiceImpl quizService = new QuizServiceImpl(GWT.create(QuizService.class), this);

    private final TextBox editQuizTitleTextBox = new TextBox();

    private final Button addNewQuestionButton = new Button("Add question");

    private final VerticalPanel quizList = new VerticalPanel();
    private final HorizontalPanel superQuizPanel = new HorizontalPanel();
    VerticalPanel questionPanel = new VerticalPanel();

    AdminQuizPanel adminQuizPanel = new AdminQuizPanel("admin-quiz-list");

    @Override
    public void onModuleLoad() {
        MyProfiler profiler = new MyProfiler();
        profiler.start();

        RootPanel rootPanel = RootPanel.get("admin-quizzes-container");
        RootPanel quizList = RootPanel.get("quizzes-list");

        FlowQuizPanel flowQuizUserPanel = new FlowQuizPanel();
        quizList.add(flowQuizUserPanel);


        flowQuizUserPanel.refresh();

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

        double endTime = profiler.end();
        logger.log(Level.INFO, "onModuleLoad was executed for " + endTime +"ms" );
    }

    public void refreshAdminPanel() {
        adminQuizPanel.refresh(quizService);
    }
}
