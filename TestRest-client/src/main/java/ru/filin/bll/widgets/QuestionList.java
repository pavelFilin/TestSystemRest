package ru.filin.bll.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.filin.DTO.QuestionFreeText;
import ru.filin.DTO.QuestionGroup;
import ru.filin.DTO.QuestionStandard;
import ru.filin.DTO.Quiz;
import ru.filin.QuizService;
import ru.filin.bll.QuizServiceImpl;

import java.util.Set;

public class QuestionList extends VerticalPanel {
    QuizService quizService = GWT.create(QuizService.class);
    DialogBox addNewQuestionDialog = new DialogBox(true);

    private Quiz quiz;

    public QuestionList() {
        initialize();
    }

    private void initialize() {

    }


    public void refresh() {
        this.clear();
        QuestionList th = this;
        quizService.findByID(quiz.getId(), new MethodCallback<Quiz>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                throw new RuntimeException();
            }

            @Override
            public void onSuccess(Method method, Quiz response) {
                th.add(new Label(new String(response.getTitle())));
                Set<QuestionStandard> questionStandard = response.getQuestionStandard();
                Set<QuestionFreeText> questionFreeTexts = response.getQuestionFreeTexts();
                Set<QuestionGroup> questionGroups = response.getQuestionGroups();

                for (int i = 0; i < response.getCountOfQuestion(); ) {
                    for (QuestionStandard question : questionStandard) {
                        HorizontalPanel questionPanel = new HorizontalPanel();
                        questionPanel.add(new Label(Integer.toString(i+1) + "."));
                        questionPanel.add(new Label(question.getText()));
                        th.add(questionPanel);
                        i++;
                    }

                    for (QuestionFreeText question : questionFreeTexts) {
                        HorizontalPanel questionPanel = new HorizontalPanel();
                        questionPanel.add(new Label(Integer.toString(i+1) + "."));
                        questionPanel.add(new Label(question.getText()));
                        th.add(questionPanel);
                        i++;
                    }

                    for (QuestionGroup question : questionGroups) {
                        HorizontalPanel questionPanel = new HorizontalPanel();
                        questionPanel.add(new Label(Integer.toString(i+1) + "."));
                        questionPanel.add(new Label(question.getText()));
                        th.add(questionPanel);
                        i++;
                    }
                }
                appendAddQuestionButton();
            }
        });
    }


    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    private void appendAddQuestionButton() {
        Button button = new Button("add question");
        this.add(button);
        button.addClickHandler(e ->  {
            addNewQuestionDialog.clear();
            addNewQuestionDialog.setText(quiz.getTitle());
            VerticalPanel verticalPanel = new VerticalPanel();
            HorizontalPanel horizontalPanel = new HorizontalPanel();
            verticalPanel.add(horizontalPanel);
            addNewQuestionDialog.add(verticalPanel);

            TextBox text = new TextBox();
            horizontalPanel.add(text);

            Button addButton = new Button("add");
            horizontalPanel.add(addButton);

            addButton.addClickHandler(event -> {
                QuestionFreeText questionFreeText = new QuestionFreeText();
                questionFreeText.setText(text.getText());
                Set<QuestionFreeText> questionFreeTexts = quiz.getQuestionFreeTexts();

                questionFreeTexts.add(questionFreeText);
                if (questionFreeText!=null) Window.alert("not null");
                quizService.updateQuiz(quiz, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        addNewQuestionDialog.hide();
                        Window.alert("N");


                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        Window.alert("Appended");
                        refresh();
                        addNewQuestionDialog.hide();
                    }
                });
            });
            addNewQuestionDialog.show();
        });
    }
}
