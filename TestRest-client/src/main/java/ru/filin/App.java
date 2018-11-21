package ru.filin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import ru.filin.DTO.QuizDTO;
import ru.filin.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {


    private final QuizService quizService = GWT.create(QuizService.class);

    @Override
    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get("quizzes-container");

        final VerticalPanel verticalPanel = new VerticalPanel();
        rootPanel.add(verticalPanel);

        quizService.findAll(new MethodCallback<List<QuizDTO>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
               throw  new RuntimeException();
            }

            @Override
            public void onSuccess(Method method, List<QuizDTO> response) {
                verticalPanel.add(new Label("Current count of quizzes:" + response.size()));
                for (QuizDTO quizDTO: response) {
                    verticalPanel.add(new Label("Title1 " + quizDTO.getTitle()));
                }
            }
        });

        Button addQuizButton = new Button("Create quiz");
        TextBox  quizTitleTextBox = new TextBox();

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

                Window.alert("Button clicked!");
            }
        });

        rootPanel.add(addQuizButton);
        rootPanel.add(quizTitleTextBox);
    }
}
