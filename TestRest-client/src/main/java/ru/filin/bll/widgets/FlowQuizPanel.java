package ru.filin.bll.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.filin.DTO.*;
import ru.filin.QuizService;
import ru.filin.bll.utils.QuizWheel;

import java.util.List;
import java.util.Set;

public class FlowQuizPanel extends FlowPanel {
    private QuizService quizService = GWT.create(QuizService.class);

    private DialogBox dialogBox = new DialogBox(false, true);

    FlowPanel th = this;


    public void refresh() {
        this.clear();

        quizService.findAll(new MethodCallback<List<Quiz>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                throw new RuntimeException("find all quiz in" + th.getClass().getSimpleName() + " didn't execution");
            }

            @Override
            public void onSuccess(Method method, List<Quiz> response) {
                response.forEach(q -> {
                    VerticalPanel verticalPanel = new VerticalPanel();
                    Label titleLabel = new Label(q.getTitle());
                    Label countOfQuestionLabel = new Label(Integer.toString(q.getCountOfQuestion()));

                    verticalPanel.add(titleLabel);
                    verticalPanel.add(countOfQuestionLabel);
                    Button button = new Button("pass");

                    button.addClickHandler(e -> openTestingDialog(q));

                    button.setStyleName("btn btn-primary");
                    verticalPanel.setStyleName("inline");
                    verticalPanel.add(button);
                    verticalPanel.setSpacing(10);

                    th.add(verticalPanel);
                });
            }

            private void openTestingDialog(Quiz quiz) {
                dialogBox.clear();
                QuizWheel quizWheel = new QuizWheel(quiz);

                DockPanel dockPanel = new DockPanel();

                Label titleLabel = new Label(quiz.getTitle());
                dockPanel.add(titleLabel, DockPanel.NORTH);

                Label numberOfQuestion = new Label("№ 1 " + "of " + quizWheel.getCountOfQuestions());
                dockPanel.add(numberOfQuestion, DockPanel.NORTH);

                Question question = quizWheel.getNext();

                Label labelText = new Label(question.getText());
                dockPanel.add(labelText, DockPanel.CENTER);

                TextBox answerTextBox = new TextBox();

                Button doAnswerButton = new Button("pass");

                doAnswerButton.addClickHandler(event -> {
                    if (quizWheel.hasNext()) {
                        Question question1 = quizWheel.getNext();

                        numberOfQuestion.setText("№ " + Integer.toString(quizWheel.getNumberOfCurrentQuestions()+1) + "of " + quizWheel.getCountOfQuestions());
                        labelText.setText(question1.getText());
                        answerTextBox.setText("");
                    } else {
                        Window.alert("finish");
                        dialogBox.hide();
                    }
                });

                dockPanel.add(doAnswerButton, DockPanel.SOUTH);
                dockPanel.add(answerTextBox, DockPanel.SOUTH);

                dialogBox.add(dockPanel);
                dialogBox.show();
            }
        });

    }
}
