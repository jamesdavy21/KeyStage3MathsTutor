package com.example.up804392.keystage3mathstutor.quiz.Questions;

import java.util.Optional;

public interface Quiz {

    Optional<Question> createQuestion(QuestionDifficulty difficulty);

}
