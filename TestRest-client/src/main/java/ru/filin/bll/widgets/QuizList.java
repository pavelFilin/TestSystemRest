package ru.filin.bll.widgets;

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.filin.DTO.Quiz;
import ru.filin.QuizService;
import ru.filin.bll.QuizServiceImpl;
import ru.filin.bll.utils.MyProfiler;
import ru.filin.bll.utils.StatsEventLogger;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizList extends HorizontalPanel {
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    Widget parent;

    private QuizList th = this;

    public QuizList() {

    }

    public QuizList(Widget parent) {
        this.parent = parent;
    }

    public void refresh(QuizServiceImpl quizService) {
        MyProfiler profiler = new MyProfiler();
        profiler.start();

        this.clear();
        List<Quiz> quizzes = quizService.getQuizzes();

        Grid grid = new Grid(quizzes.size() + 1, 2);

        this.add(grid);

        grid.setWidget(0, 0, new Label("Title"));
        grid.setWidget(0, 1, new Label("Questions"));
        for (int i = 0; i < quizzes.size(); i++) {
            Label title = new Label(quizzes.get(i).getTitle());
            chooseQuiz(title, quizzes.get(i));
            grid.setWidget(i + 1, 0, title);
            grid.setWidget(i + 1, 1, new Label(Integer.toString(quizzes.get(i).getCountOfQuestion())));
        }

        grid.addStyleName("gwt-grid");

        double endTime = profiler.end();
        logger.log(Level.INFO, this.th.getClass().getSimpleName() + " was executed for " + endTime + "ms");


    }

    public void refresh() {
        double startTime = Duration.currentTimeMillis();
        StatsEventLogger.logEvent(GWT.getModuleName(), "QuizList", "loadListings", startTime, "begin");

        MyProfiler profiler = new MyProfiler();
        profiler.start();

        QuizService quizService = GWT.create(QuizService.class);

        this.clear();
        MyProfiler profilerInFindAll = new MyProfiler();
        profilerInFindAll.start();
        quizService.findAll(new MethodCallback<List<Quiz>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                new Exception("QuizList wasn't loaded");
            }

            @Override
            public void onSuccess(Method method, List<Quiz> quizzes) {


                Grid grid = new Grid(quizzes.size() + 1, 2);

                th.add(grid);

                grid.setWidget(0, 0, new Label("Title"));
                grid.setWidget(0, 1, new Label("Questions"));
                for (int i = 0; i < quizzes.size(); i++) {
                    Label title = new Label(quizzes.get(i).getTitle());
                    chooseQuiz(title, quizzes.get(i));
                    grid.setWidget(i + 1, 0, title);
                    grid.setWidget(i + 1, 1, new Label(Integer.toString(quizzes.get(i).getCountOfQuestion())));
                }
                grid.addStyleName("gwt-grid");
                double endTime = profiler.end();
                logger.log(Level.INFO, th.getClass().getSimpleName() + " have received for " + endTime + "ms");

                double endTime2 = Duration.currentTimeMillis();
                StatsEventLogger.logEvent(GWT.getModuleName(), "QuizList", "loadListings", endTime2, "end");
            }
        });
        double endTime = profiler.end();
        logger.log(Level.INFO, this.getClass().getSimpleName() + " was loaded for " + endTime + "ms ");
    }

    private void chooseQuiz(HasClickHandlers source, Quiz quiz) {
        source.addClickHandler(event -> {
            QuestionList questionList = ((AdminQuizPanel) parent).getQuestionList();
            questionList.setQuiz(quiz);
            questionList.refresh();

            AnswerList answerList = ((AdminQuizPanel) parent).getAnswerList();
            if (answerList != null) {
                answerList.clear();
            }

        });
    }

}
