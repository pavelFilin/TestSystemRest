package ru.filin.DTO;


public class AnswerFreeText implements Answer{
    private long id;

    private String answerText;

    private QuestionFreeText questionFreeText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public QuestionFreeText getQuestionFreeText() {
        return questionFreeText;
    }

    public void setQuestionFreeText(QuestionFreeText questionFreeText) {
        this.questionFreeText = questionFreeText;
    }

    @Override
    public String getText() {
        return answerText;
    }
}
