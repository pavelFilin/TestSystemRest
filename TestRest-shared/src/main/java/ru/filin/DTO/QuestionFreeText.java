package ru.filin.DTO;

import java.util.HashSet;
import java.util.Set;

public class QuestionFreeText {

    private long id;

    private String text;

    private Quiz quiz;

    private Set<AnswerFreeText> answerFreeText = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Set<AnswerFreeText> getAnswerFreeText() {
        return answerFreeText;
    }

    public void setAnswerFreeText(Set<AnswerFreeText> answerFreeText) {
        this.answerFreeText = answerFreeText;
    }
}
