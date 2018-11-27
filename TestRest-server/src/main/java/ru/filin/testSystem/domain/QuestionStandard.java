package ru.filin.testSystem.domain;


import ru.filin.DTO.QuestionStandardDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question_standard")
public class QuestionStandard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    private String text;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Quiz quiz;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = AnswerStandard.class, mappedBy = "questionStandard")
    private Set<AnswerStandard> answerStandards = new HashSet<>();


    public QuestionStandard() {
    }



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

    public Set<AnswerStandard> getAnswerStandards() {
        return answerStandards;
    }

    public void setAnswerStandards(Set<AnswerStandard> answerStandards) {
        this.answerStandards = answerStandards;
    }
}
