package ru.filin.DTO;


import com.google.gwt.user.client.Window;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Quiz {

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
        return questionFreeTexts.size() + questionGroups.size() + questionStandard.size();
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.addAll(questionFreeTexts.stream().collect(Collectors.toList()));
        questions.addAll(questionStandard.stream().collect(Collectors.toList()));

        //todo finish question group
//        questions.addAll(questionGroups.stream().collect(Collectors.toList()));
        return questions;
    }
}
