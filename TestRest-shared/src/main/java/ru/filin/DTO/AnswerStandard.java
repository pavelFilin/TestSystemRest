package ru.filin.DTO;


public class AnswerStandard {
    private long id;

    private String answerText;

    private boolean isRightAnswer;

    private QuestionStandard questionStandard;

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

    public QuestionStandard getQuestionStandard() {
        return questionStandard;
    }

    public void setQuestionStandard(QuestionStandard questionStandard) {
        this.questionStandard = questionStandard;
    }
}
