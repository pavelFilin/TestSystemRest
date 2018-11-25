package ru.filin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.filin.DTO.QuizDTO;

import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {


    private final QuizService quizService = GWT.create(QuizService.class);

    private final DialogBox editQuizDialogBox = new DialogBox();

    private final DialogBox addQuestionDialogBox = new DialogBox();

    private final TextBox editQuizTitleTextBox = new TextBox();

    private final Button addNewQuestionButton = new Button("Add question");

    private final TextBox questionText = new TextBox();

    @Override
    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get("quizzes-container");

        final VerticalPanel verticalPanel = new VerticalPanel();
        rootPanel.add(verticalPanel);

        quizService.findAll(new MethodCallback<List<QuizDTO>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                throw new RuntimeException();
            }

            @Override
            public void onSuccess(Method method, List<QuizDTO> response) {
                verticalPanel.add(new Label("Current count of quizzes:" + response.size()));
                for (QuizDTO quizDTO : response) {
                    Label quizLabel = new Label(quizDTO.getTitle().toUpperCase() + "    " + quizDTO.getCountOfQuestion() + " questions");
                    quizLabel.addClickHandler(clickEvent -> {
                        showEditQuizDialog(quizDTO);
                    });
                    verticalPanel.add(quizLabel);
                }
            }
        });

        Button addQuizButton = new Button("Create quiz");
        TextBox quizTitleTextBox = new TextBox();

        addQuizButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                QuizDTO quizDTO = new QuizDTO();
                quizDTO.setTitle(quizTitleTextBox.getValue());
                quizService.addQuiz(quizDTO, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("error adding new quiz");
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        Window.alert("Success");
                    }
                });
            }
        });


        rootPanel.add(addQuizButton);
        rootPanel.add(quizTitleTextBox);
    }

    private DialogBox showEditQuizDialog(QuizDTO quizDTO) {

        final DialogBox dialog = new DialogBox(true, true);
        FlowPanel flowPanel = new FlowPanel();
        dialog.setText("Edit quiz");
        editQuizTitleTextBox.setValue(quizDTO.getTitle());
        dialog.add(editQuizTitleTextBox);

        dialog.add(addNewQuestionButton);

        if (quizDTO.getQuestionFreeTextDTOS() != null) {
            Window.alert(quizDTO.getQuestionFreeTextDTOS().toString());
            dialog.add(new Label("Free text questions - " + quizDTO.getQuestionFreeTextDTOS().size()));
            quizDTO.getQuestionFreeTextDTOS().stream().forEach(q -> {
                dialog.add(new Label(q.getText()));
            });
        }

        if (quizDTO.getQuestionStandardDTO() != null) {
            dialog.add(new Label("Standard questions - " + quizDTO.getQuestionStandardDTO().size()));
            quizDTO.getQuestionStandardDTO().stream().forEach(q -> {
                dialog.add(new Label(q.getText()));
            });
        }

        if (quizDTO.getQuestionGroupDTOS() != null) {
            dialog.add(new Label("Group questions - " + quizDTO.getQuestionGroupDTOS().size()));
            quizDTO.getQuestionGroupDTOS().stream().forEach(q -> {
                dialog.add(new Label(q.getText()));
            });
        }

        dialog.setPopupPosition(100, 150);
        dialog.show();
        return dialog;
    }

    private void initAddNewQuestionButton(){
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
}
