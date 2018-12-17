package ru.filin.testSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "answer_standard")
public class AnswerStandard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String answerText;

    private boolean isRightAnswer;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
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
