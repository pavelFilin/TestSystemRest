package ru.filin.testSystem.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question_free_text")
public class QuestionFreeText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    private String text;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Quiz quiz;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = AnswerFreeText.class, mappedBy = "questionFreeText")
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
