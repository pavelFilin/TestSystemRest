package ru.filin.bll.widgets;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import ru.filin.DTO.Quiz;
import ru.filin.QuizService;
import ru.filin.bll.QuizServiceImpl;

import java.util.List;

public class QuizList extends HorizontalPanel {

    Widget parent;

    public QuizList() {

    }

    public QuizList(Widget parent) {
        this.parent = parent;
    }

    public void refresh(QuizServiceImpl quizService) {
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

    }

    private void chooseQuiz(Label label, Quiz quiz) {
        label.addClickHandler(event -> {
            QuestionList questionList = ((AdminQuizPanel) parent).getQuestionList();
            questionList.setQuiz(quiz);
            questionList.refresh();
        });
    }

}
