package ru.filin.bll.widgets;

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.filin.DTO.*;
import ru.filin.QuizService;
import ru.filin.bll.utils.MyProfiler;
import ru.filin.bll.utils.QuizWheel;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlowQuizPanel extends FlowPanel {
    static final Logger logger = Logger.getLogger(FlowQuizPanel.class.getSimpleName());

    private QuizService quizService = GWT.create(QuizService.class);

    private DialogBox dialogBox = new DialogBox(false, true);

    FlowPanel th = this;


    public void refresh() {

        MyProfiler profiler = new MyProfiler();
        profiler.start();
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
                    Button passButton = new Button("pass");

                    passButton.addClickHandler(e -> openTestingDialog(q));

                    passButton.setStyleName("btn btn-primary");
                    verticalPanel.setStyleName("inline");
                    verticalPanel.add(passButton);
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

                        numberOfQuestion.setText("№ " + Integer.toString(quizWheel.getNumberOfCurrentQuestions() + 1) + " of " + quizWheel.getCountOfQuestions());
                        labelText.setText(question1.getText());
                        answerTextBox.setText("");
                    } else {
                        dialogBox.hide();
                    }
                });

                dockPanel.add(doAnswerButton, DockPanel.SOUTH);
                dockPanel.add(answerTextBox, DockPanel.SOUTH);

                dialogBox.add(dockPanel);
                dialogBox.show();
            }
        });
        double endTime = profiler.end();
        logger.log(Level.INFO, this.getClass().getSimpleName() + " was loaded for " + endTime + "ms ");

    }
}
