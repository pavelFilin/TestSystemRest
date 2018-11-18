package ru.filin.DTO;


import java.util.HashSet;
import java.util.Set;

public class QuestionStandardDTO {

    private long id;

    private String text;

    private QuizDTO quizDTO;

    private Set<AnswerStandardDTO> answerStandardDTOS = new HashSet<>();

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

    public QuizDTO getQuizDTO() {
        return quizDTO;
    }

    public void setQuizDTO(QuizDTO quizDTO) {
        this.quizDTO = quizDTO;
    }

    public Set<AnswerStandardDTO> getAnswerStandardDTOS() {
        return answerStandardDTOS;
    }

    public void setAnswerStandardDTOS(Set<AnswerStandardDTO> answerStandardDTOS) {
        this.answerStandardDTOS = answerStandardDTOS;
    }
}
