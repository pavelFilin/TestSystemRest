package ru.filin.testSystem.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", length = 50)
    private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = QuestionStandard.class, mappedBy = "quiz")
    private Set<QuestionStandard> questionStandard = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = QuestionFreeText.class, mappedBy = "quiz")
    private Set<QuestionFreeText> questionFreeTexts = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = QuestionGroup.class, mappedBy = "quiz")
    private Set<QuestionGroup> questionGroups = new HashSet<>();


    public Quiz() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<QuestionStandard> getQuestionStandard() {
        return questionStandard;
    }

    public void setQuestionStandard(Set<QuestionStandard> questionStandard) {
        this.questionStandard = questionStandard;
    }

    public void setQuestionFreeTexts(Set<QuestionFreeText> questionFreeTexts) {
        this.questionFreeTexts = questionFreeTexts;
    }

    public Set<QuestionGroup> getQuestionGroups() {
        return questionGroups;
    }

    public void setQuestionGroups(Set<QuestionGroup> questionGroups) {
        this.questionGroups = questionGroups;
    }

    public Set<QuestionFreeText> getQuestionFreeTexts() {
        return questionFreeTexts;
    }
}
