package com.example.up804392.keystage3mathstutor.quiz.Questions;

import java.util.Optional;

public interface Question {


    String solveQuestion();

    Optional<String> getQuestion();

    <T> boolean isAnswerCorrect(T value);

}
