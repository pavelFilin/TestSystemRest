package ru.filin.DTO;

import java.util.List;

public interface Question {
    String getText();
    List<Answer> getAnswers();
}
