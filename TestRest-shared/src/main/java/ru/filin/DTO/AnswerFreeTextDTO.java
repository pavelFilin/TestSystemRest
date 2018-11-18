package ru.filin.DTO;


public class AnswerFreeTextDTO {

    private long id;

    private String answerText;

    private QuestionFreeTextDTO questionFreeTextDTO;

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

    public QuestionFreeTextDTO getQuestionFreeTextDTO() {
        return questionFreeTextDTO;
    }

    public void setQuestionFreeTextDTO(QuestionFreeTextDTO questionFreeTextDTO) {
        this.questionFreeTextDTO = questionFreeTextDTO;
    }
}
