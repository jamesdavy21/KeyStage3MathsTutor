package com.example.up804392.keystage3mathstutor.quiz.Questions;

import java.util.Optional;

public interface Question {


    <T> T solveQuestion();

    <T> Optional<T> getQuestion();

    <T> boolean isAnswerCorrect(T value);

}
