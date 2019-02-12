package ru.filin.bll.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.filin.DTO.*;
import ru.filin.QuizService;

import java.util.Set;

public class QuestionList extends VerticalPanel {
    private AdminQuizPanel parent;
    QuizService quizService = GWT.create(QuizService.class);
    DialogBox addNewQuestionDialog = new DialogBox(true);

    private Quiz quiz;

    public QuestionList() {
        initialize();
    }

    public QuestionList(AdminQuizPanel parent) {
        this.parent = parent;
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
                th.add(new Label("questions of " + new String(response.getTitle())));
                Set<QuestionStandard> questionStandard = response.getQuestionStandard();
                Set<QuestionFreeText> questionFreeTexts = response.getQuestionFreeTexts();
                Set<QuestionGroup> questionGroups = response.getQuestionGroups();

                for (int i = 0; i < response.getCountOfQuestion(); ) {
                    for (QuestionStandard question : questionStandard) {
                        HorizontalPanel questionPanel = new HorizontalPanel();
                        questionPanel.add(new Label(Integer.toString(i + 1) + "."));
                        Label textLabel = new Label(question.getText());
                        chooseQuiz(textLabel, question);
                        Label typeQuestionLabel = new Label("Standard");
                        questionPanel.add(typeQuestionLabel);
                        questionPanel.add(textLabel);
                        th.add(questionPanel);
                        i++;
                    }

                    for (QuestionFreeText question : questionFreeTexts) {
                        HorizontalPanel questionPanel = new HorizontalPanel();
                        questionPanel.add(new Label(Integer.toString(i + 1) + "."));
                        Label textLabel = new Label(question.getText());
                        chooseQuiz(textLabel, question);
                        Label typeQuestionLabel = new Label("Free text");
                        questionPanel.add(typeQuestionLabel);
                        questionPanel.add(textLabel);
                        th.add(questionPanel);
                        i++;
                    }

                    for (QuestionGroup question : questionGroups) {
                        HorizontalPanel questionPanel = new HorizontalPanel();
                        questionPanel.add(new Label(Integer.toString(i + 1) + "."));
                        Label textLabel = new Label(question.getText());
                        Label typeQuestionLabel = new Label("Group");
                        questionPanel.add(typeQuestionLabel);
                        chooseQuiz(textLabel, question);
                        questionPanel.add(textLabel);
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
        button.addClickHandler(e -> {
            showAddNewQuestionDialog();
        });
    }

    private void showAddNewQuestionDialog() {
        QuestionType questionType = QuestionType.FREE_TEXT;

        addNewQuestionDialog.clear();
        addNewQuestionDialog.setText(quiz.getTitle());

        DockPanel dockPanel = new DockPanel();
        addNewQuestionDialog.add(dockPanel);

        //VerticalPanel verticalPanel = new VerticalPanel();
        // HorizontalPanel horizontalPanel = new HorizontalPanel();
        //verticalPanel.add(horizontalPanel);
        // addNewQuestionDialog.add(verticalPanel);

        TextBox text = new TextBox();
        dockPanel.add(text, DockPanel.CENTER);

        //horizontalPanel.add(text);

        Button addButton = new Button("add");
        // horizontalPanel.add(addButton);
        dockPanel.add(addButton, DockPanel.SOUTH);


        ListBox typeQuestionListBox = new ListBox();
        for (QuestionType type : QuestionType.values()) {
            typeQuestionListBox.addItem(type.name());
        }
        dockPanel.add(typeQuestionListBox, DockPanel.NORTH);


        addButton.addClickHandler(event -> {
            switch (QuestionType.valueOf(typeQuestionListBox.getSelectedItemText())) {
                case FREE_TEXT:
                    addNewQuestionFreeText(text.getText());
                    break;
                case STANDARD:
                    addNewQuestionStandard(text.getText());
                    break;
                case GROUP:
                    addNewQuestionGroup(text.getText());
                    break;
            }

        });
        addNewQuestionDialog.show();
    }

    private void addNewQuestionFreeText(String text) {
        QuestionFreeText question = new QuestionFreeText();
        question.setText(text);
        Set<QuestionFreeText> questionFreeTexts = quiz.getQuestionFreeTexts();
        questionFreeTexts.add(question);
        saveUpdate();
    }

    private void addNewQuestionStandard(String text) {
        QuestionStandard question = new QuestionStandard();
        question.setText(text);
        Set<QuestionStandard> qeestionStandards = quiz.getQuestionStandard();
        qeestionStandards.add(question);
        saveUpdate();
    }

    private void addNewQuestionGroup(String text) {
        QuestionGroup question = new QuestionGroup();
        question.setText(text);
        Set<QuestionGroup> questionGroups = quiz.getQuestionGroups();
        questionGroups.add(question);
        saveUpdate();
    }

    private void saveUpdate() {
        quizService.updateQuiz(quiz, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                addNewQuestionDialog.hide();
            }

            @Override
            public void onSuccess(Method method, Void response) {
                refresh();
                parent.getQuizList().refresh();
                        addNewQuestionDialog.hide();
            }
        });
    }

    private void chooseQuiz(HasClickHandlers source, QuestionStandard questionStandard) {
        source.addClickHandler(event -> {
            AnswerList answerList = ((AdminQuizPanel) parent).getAnswerList();
            answerList.setQuiz(quiz);
            answerList.setQuestion(questionStandard);
            answerList.refresh();
        });
    }

    private void chooseQuiz(HasClickHandlers source, QuestionFreeText questionFreeText) {
        source.addClickHandler(event -> {
            AnswerList answerList = ((AdminQuizPanel) parent).getAnswerList();
            answerList.setQuiz(quiz);
            answerList.setQuestion(questionFreeText);
            answerList.refresh();
        });
    }

    private void chooseQuiz(HasClickHandlers source, QuestionGroup questionGroup) {
        source.addClickHandler(event -> {
            AnswerList answerList = ((AdminQuizPanel) parent).getAnswerList();
            answerList.setQuiz(quiz);
            answerList.setQuestion(questionGroup);
            answerList.refresh();
        });
    }
}
