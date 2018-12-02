package ru.filin.DTO;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Quiz implements Serializable {

    private long id;

    private String title;

    private Set<QuestionStandard> questionStandard = new HashSet<>();

    private Set<QuestionFreeText> questionFreeTexts = new HashSet<>();

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

    public Set<QuestionFreeText> getQuestionFreeTexts() {
        return questionFreeTexts;
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

    public int getCountOfQuestion() {
        return 0;
    }
}
