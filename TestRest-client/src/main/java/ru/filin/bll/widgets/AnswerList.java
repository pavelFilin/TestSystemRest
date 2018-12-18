package ru.filin.bll.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.filin.DTO.*;
import ru.filin.QuizService;

public class AnswerList extends VerticalPanel {
    private QuizService quizService = GWT.create(QuizService.class);

    DialogBox dialogBox = new DialogBox(true);

    private QuestionFreeText questionFreeText;
    private QuestionStandard questionStandard;
    private QuestionGroup questionGroup;

    private Quiz quiz;

    private Widget parent;

    private AnswerList th = this;

    public AnswerList() {

    }

    public AnswerList(Widget parent) {
        this.parent = parent;
    }

    public void refresh() {
        this.clear();

        quizService.findByID(quiz.getId(), new MethodCallback<Quiz>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                throw new RuntimeException();
            }

            @Override
            public void onSuccess(Method method, Quiz response) {

                int count = 0;
                if (questionStandard != null) {
                    questionStandard = response.getQuestionStandard().stream().filter(x->x.getId()==questionStandard.getId()).findFirst().get();
                    Label textLabel = new Label(th.questionStandard.getText());
                    th.add(textLabel);
                    for (AnswerStandard answerStandard : questionStandard.getAnswerStandards()) {
                        questionStandard = response.getQuestionStandard().stream().filter(x->x.getId()==questionStandard.getId()).findFirst().get();

                        HorizontalPanel answerPanel = new HorizontalPanel();
                        answerPanel.add(new Label(++count + ". " + answerStandard.getAnswerText()));

                        CheckBox checkBox = new CheckBox("right");
                        checkBox.setEnabled(false);
                        checkBox.setValue(answerStandard.isRightAnswer());
                        answerPanel.add(checkBox);

                        th.add(answerPanel);
                    }
                } else if (questionFreeText != null) {
                    questionFreeText = response.getQuestionFreeTexts().stream().filter(x->x.getId()==questionFreeText.getId()).findFirst().get();


                    Label textLabel = new Label("answers of " + th.questionFreeText.getText());
                    th.add(textLabel);
                    for (AnswerFreeText answerFreeText : questionFreeText.getAnswerFreeText()) {
                        HorizontalPanel answerPanel = new HorizontalPanel();
                        answerPanel.add(new Label(++count + ". " + answerFreeText.getAnswerText()));
                        th.add(answerPanel);
                    }

                } else if (questionGroup != null) {
                    questionGroup = response.getQuestionGroups().stream().filter(x->x.getId()==questionGroup.getId()).findFirst().get();

                    Label textLabel = new Label(th.questionGroup.getText());
                    th.add(textLabel);
                    //todo answerGroup isn't realized;
                }

                Button addButton = new Button("add answer");
                addButton.addClickHandler(e ->
                        showAddAnswerDialog()
                );
                th.add(addButton);
            }
        });


    }

    private void showAddAnswerDialog() {
        dialogBox.clear();

        DockPanel dockPanel = new DockPanel();
        dialogBox.add(dockPanel);

        Label infoLabel = new Label();
        dockPanel.add(infoLabel, DockPanel.NORTH);

        TextBox answerText = new TextBox();
        dockPanel.add(answerText, DockPanel.CENTER);

        CheckBox isRightAnswer = new CheckBox("right answer", true);

        QuestionType questionType = QuestionType.FREE_TEXT;

        if (questionStandard != null) {
            questionType = QuestionType.STANDARD;
            infoLabel.setText("Standard");
            dockPanel.add(isRightAnswer, DockPanel.EAST);
        } else if (questionFreeText != null) {
            questionType = QuestionType.FREE_TEXT;
            infoLabel.setText("Free Text");
        } else if (questionGroup != null) {
            questionType = QuestionType.GROUP;
            infoLabel.setText("Group");
            dockPanel.add(isRightAnswer, DockPanel.EAST);
        }

        Button add = new Button("add");

        QuestionType finalQuestionType = questionType;
        add.addClickHandler(event -> {
            switch (finalQuestionType) {
                case STANDARD: {
                    AnswerStandard answer= new AnswerStandard();
                    answer.setAnswerText(answerText.getText());

                    QuestionStandard question = quiz.getQuestionStandard()
                            .stream()
                            .filter(x -> x.getId() == questionStandard.getId())
                            .findFirst()
                            .orElseThrow(RuntimeException::new);

                    answer.setRightAnswer(isRightAnswer.getValue());
                    question.getAnswerStandards().add(answer);
                    updateQuiz(quiz);
                    dialogBox.hide();
                }
                break;

                case GROUP: {

                }
                break;

                case FREE_TEXT: {
                    AnswerFreeText answerFreeText = new AnswerFreeText();
                    answerFreeText.setAnswerText(answerText.getText());

                    QuestionFreeText question = quiz.getQuestionFreeTexts().stream().filter(x -> x.getId() == questionFreeText.getId()).findFirst().orElseThrow(RuntimeException::new);

                    question.getAnswerFreeText().add(answerFreeText);
                    updateQuiz(quiz);
                    dialogBox.hide();
                }
                break;
            }
        });


        dockPanel.add(add, DockPanel.SOUTH);

        dialogBox.show();

    }

    private void updateQuiz(Quiz quiz) {
        quizService.updateQuiz(quiz, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                throw new RuntimeException();
            }

            @Override
            public void onSuccess(Method method, Void response) {
                th.refresh();
            }
        });
    }

    public void setQuestion(QuestionFreeText question) {
        this.questionFreeText = null;
        this.questionGroup = null;
        this.questionStandard = null;

        this.questionFreeText = question;
    }

    public void setQuestion(QuestionGroup question) {
        this.questionFreeText = null;
        this.questionGroup = null;
        this.questionStandard = null;

        this.questionGroup = question;
    }

    public void setQuestion(QuestionStandard question) {
        this.questionFreeText = null;
        this.questionGroup = null;
        this.questionStandard = null;

        this.questionStandard = question;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
