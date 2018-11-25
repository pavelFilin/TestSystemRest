package ru.filin.DTO;

import java.util.HashSet;
import java.util.Set;

public class QuizDTO {
    private long id;

    private String title;

    private Set<QuestionStandardDTO> questionStandardDTO = new HashSet<>();

    private Set<QuestionFreeTextDTO> questionFreeTextDTOS = new HashSet<>();

    private Set<QuestionGroupDTO> questionGroupDTOS = new HashSet<>();


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

    public int getCountOfQuestion() {
        int count = 0;
        if (questionStandardDTO != null) {
            count+= questionStandardDTO.size();
        }

        if (questionFreeTextDTOS != null) {
            count+=questionFreeTextDTOS.size();
        }

        if (questionGroupDTOS != null) {
            count+=questionGroupDTOS.size();
        }


        return count;
    }

    public Set<QuestionStandardDTO> getQuestionStandardDTO() {
        return questionStandardDTO;
    }

    public void setQuestionStandardDTO(Set<QuestionStandardDTO> questionStandardDTO) {
        this.questionStandardDTO = questionStandardDTO;
    }

    public Set<QuestionFreeTextDTO> getQuestionFreeTextDTOS() {
        return questionFreeTextDTOS;
    }

    public void setQuestionFreeTextDTOS(Set<QuestionFreeTextDTO> questionFreeTextDTOS) {
        this.questionFreeTextDTOS = questionFreeTextDTOS;
    }

    public Set<QuestionGroupDTO> getQuestionGroupDTOS() {
        return questionGroupDTOS;
    }

    public void setQuestionGroupDTOS(Set<QuestionGroupDTO> questionGroupDTOS) {
        this.questionGroupDTOS = questionGroupDTOS;
    }

}
