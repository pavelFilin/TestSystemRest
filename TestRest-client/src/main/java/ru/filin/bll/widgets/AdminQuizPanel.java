package ru.filin.bll.widgets;


import com.google.gwt.user.client.ui.HorizontalPanel;
import ru.filin.bll.QuizServiceImpl;

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
        quizList.refresh(quizServiceImpl);
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
