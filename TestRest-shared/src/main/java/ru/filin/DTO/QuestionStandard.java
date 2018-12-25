package ru.filin.DTO;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionStandard implements Question {

    private long id;

    private String text;

    private Quiz quiz;

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

    @Override
    public List<Answer> getAnswers() {
        return answerStandards.stream().collect(Collectors.toList());
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
