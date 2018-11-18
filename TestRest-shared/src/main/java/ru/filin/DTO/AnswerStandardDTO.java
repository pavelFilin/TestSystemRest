package ru.filin.DTO;

public class AnswerStandardDTO {

    private long id;

    private String answerText;

    private boolean isRightAnswer;

    private QuestionStandardDTO questionStandardDTO;

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

    public boolean isRightAnswer() {
        return isRightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        isRightAnswer = rightAnswer;
    }

    public QuestionStandardDTO getQuestionStandardDTO() {
        return questionStandardDTO;
    }

    public void setQuestionStandardDTO(QuestionStandardDTO questionStandardDTO) {
        this.questionStandardDTO = questionStandardDTO;
    }
}
