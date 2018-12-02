package ru.filin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.filin.DTO.QuestionFreeText;
import ru.filin.DTO.QuestionStandard;
import ru.filin.DTO.QuestionType;
import ru.filin.DTO.Quiz;
import ru.filin.bll.QuizServiceImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    private final QuizServiceImpl quizService = new QuizServiceImpl(GWT.create(QuizService.class), this);

    private final TextBox editQuizTitleTextBox = new TextBox();

    private final Button addNewQuestionButton = new Button("Add question");

    private final TextBox questionText = new TextBox();

    private final VerticalPanel quizList = new VerticalPanel();
    private final HorizontalPanel superQuizPanel = new HorizontalPanel();
    VerticalPanel questionPanel = new VerticalPanel();

    @Override
    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get("quizzes-container");

        rootPanel.add(superQuizPanel);

        quizService.findAll();

        Button addQuizButton = new Button("Create quiz");
        TextBox quizTitleTextBox = new TextBox();

        addQuizButton.addClickHandler(event -> {
            Quiz quiz = new Quiz();
            quiz.setTitle(quizTitleTextBox.getValue());
            quizService.addQuiz(quiz);
            refreshQuizList();
        });


        rootPanel.add(addQuizButton);
        rootPanel.add(quizTitleTextBox);
    }


    private DialogBox showEditQuizDialog(Quiz quiz) {

        final DialogBox dialog = new DialogBox(true, true);
        FlowPanel flowPanel = new FlowPanel();
        dialog.setText("Edit quiz");
        editQuizTitleTextBox.setValue(quiz.getTitle());
        dialog.add(editQuizTitleTextBox);

        dialog.add(addNewQuestionButton);

        if (quiz.getQuestionFreeTexts() != null) {
            Window.alert(quiz.getQuestionFreeTexts().toString());
            dialog.add(new Label("Free text questions - " + quiz.getQuestionFreeTexts().size()));
            quiz.getQuestionFreeTexts().stream().forEach(q -> {
                dialog.add(new Label(q.getText()));
            });
        }

        if (quiz.getQuestionStandard() != null) {
            dialog.add(new Label("Standard questions - " + quiz.getQuestionStandard().size()));
            quiz.getQuestionStandard().stream().forEach(q -> {
                dialog.add(new Label(q.getText()));
            });
        }

        if (quiz.getQuestionGroups() != null) {
            dialog.add(new Label("Group questions - " + quiz.getQuestionGroups().size()));
            quiz.getQuestionGroups().stream().forEach(q -> {
                dialog.add(new Label(q.getText()));
            });
        }

        dialog.setPopupPosition(100, 150);
        dialog.show();
        return dialog;
    }

    private void initAddNewQuestionButton() {
        addNewQuestionButton.addClickHandler(clickEvent -> {

        });
    }

    private void showAddQuestionDialog() {

    }

    private void initAddQuestionDialogBox() {
        ListBox listBox = new ListBox();
        listBox.addItem("Free text");
        listBox.addItem("Standard");
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(listBox);
        horizontalPanel.setSpacing(2);

        horizontalPanel.add(questionText);

        Button send = new Button("send");
        horizontalPanel.add(send);
    }


    public void refreshQuizList() {
        quizList.clear();

        List<Quiz> quizzes = quizService.getQuizzes();

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        Grid grid = new Grid(quizzes.size() + 1, 2);

        horizontalPanel.add(grid);


        grid.setWidget(0, 0, new Label("Title"));
        grid.setWidget(0, 1, new Label("Questions"));
        for (int i = 0; i < quizzes.size(); i++) {
            Label title = new Label(quizzes.get(i).getTitle());
            chooseQuiz(title);
            grid.setWidget(i + 1, 0, title);
            grid.setWidget(i + 1, 1, new Label(Integer.toString(quizzes.get(i).getCountOfQuestion())));
        }

        grid.addStyleName("gwt-grid");
        superQuizPanel.add(grid);

        VerticalPanel questionPanel = new VerticalPanel();
        questionPanel.add(new Label("questions"));

        questionPanel.add(questionPanel);
        questionPanel.add(new Label("Questions list1"));

    }


    private void chooseQuiz(Label widget) {
        widget.addClickHandler(clickEvent -> {
            questionPanel.clear();
            Quiz quiz = null;
            for (Quiz item : quizService.getQuizzes()) {
                if (item.getTitle().equals(widget.getText())) {
                    quiz = item;
                }
            }

            questionPanel.add(new Label("Questions list of " + quiz.getTitle()));

            if (quiz == null) throw new IllegalArgumentException();

            HorizontalPanel row = new HorizontalPanel();

            Set<QuestionStandard> questionStandards = quiz.getQuestionStandard();


            for (int i = 0; i < quiz.getCountOfQuestion(); i++) {
                if (quiz.getQuestionStandard() == null) {
                    Iterator<QuestionStandard> iteratorQuestionStandardDTO = questionStandards.iterator();
                    if (iteratorQuestionStandardDTO.hasNext()) {
                        QuestionStandard question = iteratorQuestionStandardDTO.next();
                        row.add(new Label(i + 1 + "."));
                        row.add(new Label(question.getText()));
                        row.add(new Label(QuestionType.STANDARD.name()));
                    }
                }
            }

            questionPanel.add(row);
            Button addQuestionButton = new Button("add question");
            questionPanel.add(addQuestionButton);

            //todo fix final
            Quiz temp = quiz;
            addQuestionButton.addClickHandler(clickEvent1 -> {
                superQuizPanel.clear();
                DialogBox newQuestionBox = new DialogBox();
                VerticalPanel verticalPanel = new VerticalPanel();
                ListBox questionType = new ListBox();
                for (QuestionType type : QuestionType.values()) {
                    questionType.addItem(type.name());
                }


                verticalPanel.add(questionType);
                TextBox questionText = new TextBox();
                verticalPanel.add(questionText);

                Button sendNewQuestion = new Button("ok");
                Button close = new Button("close");

                sendNewQuestion.addClickHandler(e -> {
                    if (temp.getQuestionFreeTexts() == null) {
                        HashSet<QuestionFreeText> questionFreeText1 = new HashSet<>();
                        QuestionFreeText question = new QuestionFreeText();
                        question.setText(questionText.getText());

                        //todo add answer
                        question.setAnswerFreeText(null);
                        questionFreeText1.add(question);
                        temp.setQuestionFreeTexts(questionFreeText1);

//                        GWT.log(temp.getQuestionFreeText().toString());
//                        Window.alert(temp.getQuestionFreeText().toString());

                        Window.alert("s" + Boolean.toString( temp.getQuestionStandard() == null));
                        Window.alert("f" + Boolean.toString( temp.getQuestionFreeTexts() == null));
                        Window.alert("g" + Boolean.toString( temp.getQuestionGroups() == null));
                        quizService.update(temp);


                    } else {
                        Window.alert("Questions was");
                    }
                });

                HorizontalPanel horizontalPanel = new HorizontalPanel();
                horizontalPanel.add(close);
                horizontalPanel.add(sendNewQuestion);


                verticalPanel.add(horizontalPanel);
                verticalPanel.add(sendNewQuestion);

                newQuestionBox.add(verticalPanel);

                newQuestionBox.show();


            });

            superQuizPanel.add(questionPanel);
        });
    }
}
