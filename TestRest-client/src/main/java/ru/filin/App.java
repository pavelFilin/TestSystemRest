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
import ru.filin.bll.widgets.AdminQuizPanel;

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

    private final VerticalPanel quizList = new VerticalPanel();
    private final HorizontalPanel superQuizPanel = new HorizontalPanel();
    VerticalPanel questionPanel = new VerticalPanel();

    AdminQuizPanel adminQuizPanel = new AdminQuizPanel("admin-quiz-list");

    @Override
    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get("quizzes-container");


//        superQuizPanel.add(quizList);
//        rootPanel.add(superQuizPanel);
        rootPanel.add(adminQuizPanel);

        quizService.findAll();

        Button addQuizButton = new Button("Create quiz");
        TextBox quizTitleTextBox = new TextBox();

        addQuizButton.addClickHandler(event -> {
            Quiz quiz = new Quiz();
            quiz.setTitle(quizTitleTextBox.getValue());
            quizService.addQuiz(quiz);
            adminQuizPanel.refresh(quizService);
//            refreshQuizList();
        });


        rootPanel.add(addQuizButton);
        rootPanel.add(quizTitleTextBox);
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

        quizList.add(grid);
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
                int i=0;
                Iterator<QuestionStandard> iteratorQuestionStandard = quiz.getQuestionStandard().iterator();
                while (iteratorQuestionStandard.hasNext()){
                    HorizontalPanel row = new HorizontalPanel();
                    QuestionStandard question = iteratorQuestionStandard.next();
                    row.add(new Label( + 1 + ". "));
                    row.add(new Label(question.getText()));
                    row.add(new Label(QuestionType.STANDARD.name()));
                    questionPanel.add(row);
                }


                Iterator<QuestionFreeText> questionFreeTextIterator = quiz.getQuestionFreeTexts().iterator();
                while (questionFreeTextIterator.hasNext()) {
                    HorizontalPanel row = new HorizontalPanel();
                    QuestionFreeText question = questionFreeTextIterator.next();
                    row.add(new Label(i + 1 + ". "));
                    row.add(new Label(question.getText()));
                    row.add(new Label(QuestionType.FREE_TEXT.name()));
                    questionPanel.add(row);
                }



            Button addQuestionButton = new Button("add question");
            questionPanel.add(addQuestionButton);

            //todo fix final
            Quiz temp = quiz;
            addQuestionButton.addClickHandler(clickEvent1 -> {
                DialogBox newQuestionBox = new DialogBox(true);

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

                close.addClickHandler(e -> newQuestionBox.hide());

                sendNewQuestion.addClickHandler(e -> {
                    if (temp.getQuestionFreeTexts() == null) {

                        Set<QuestionFreeText> questionFreeTexts = new HashSet<>();
                        QuestionFreeText question = new QuestionFreeText();
                        question.setText(questionText.getText());
                        //todo add answer

                        questionFreeTexts.add(question);
                        question.setQuiz(temp);
                        temp.setQuestionFreeTexts(questionFreeTexts);

                        quizService.update(temp);

                    } else {
                        QuestionFreeText question = new QuestionFreeText();
                        question.setText(questionText.getText());

                        //todo add answer
//                        question.setQuiz(temp);
                        temp.getQuestionFreeTexts().add(question);

                        quizService.update(temp);
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

    public void refreshAdminPanel() {
        adminQuizPanel.refresh(quizService);
    }
}
