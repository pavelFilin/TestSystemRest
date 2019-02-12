package ru.filin.bll.utils;

import ru.filin.DTO.Question;
import ru.filin.DTO.Quiz;

import java.util.List;

public class QuizWheel {
    private Quiz quiz;

    private int numberOfCurrentQuestions = -1;
    private int countOfQuestions;

    private List<Question> questions;


    public QuizWheel(Quiz quiz) {
        this.quiz = quiz;
        countOfQuestions = quiz.getCountOfQuestion();
        questions = quiz.getAllQuestions();
    }

    public Question getNext() {
        if (numberOfCurrentQuestions < countOfQuestions) {
            numberOfCurrentQuestions++;
            return questions.get(numberOfCurrentQuestions);
        }

        return null;
    }

    public int getNumberOfCurrentQuestions() {
        return numberOfCurrentQuestions;
    }

    public int getCountOfQuestions() {
        return countOfQuestions;
    }

    public boolean hasNext() {
        return (numberOfCurrentQuestions < countOfQuestions - 1) ? true : false;
    }
}
