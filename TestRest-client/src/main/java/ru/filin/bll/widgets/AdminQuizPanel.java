package ru.filin.bll.widgets;


import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import ru.filin.bll.QuizServiceImpl;
import ru.filin.bll.utils.StatsEventLogger;

public class AdminQuizPanel extends HorizontalPanel {
    private QuizList quizList = new QuizList(this);
    private QuestionList questionList = new QuestionList(this);
    private AnswerList answerList = new AnswerList(this);

    public AdminQuizPanel(String id) {
        initialize();
    }

    public void initialize() {
        this.add(quizList);
        this.add(questionList);
        this.add(answerList);
    }

    public void refresh(QuizServiceImpl quizServiceImpl) {
        double startTime = Duration.currentTimeMillis();
        StatsEventLogger.logEvent(GWT.getModuleName(), "listings", "loadListings", startTime, "begin");
        quizList.refresh(quizServiceImpl);
        double endTime = Duration.currentTimeMillis();
        StatsEventLogger.logEvent(GWT.getModuleName(), "listings", "loadListings", endTime, "end");

    }

    public QuizList getQuizList() {
        return quizList;
    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public AnswerList getAnswerList() {
        return answerList;
    }
}
