package ru.filin.testSystem.domain;

import javax.persistence.*;

@Entity
@Table(name = "Answer_free_text")
public class AnswerFreeText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String answerText;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
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
}
