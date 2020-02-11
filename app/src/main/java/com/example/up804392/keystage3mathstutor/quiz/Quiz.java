package com.example.up804392.keystage3mathstutor.quiz;

import com.example.up804392.keystage3mathstutor.quiz.Questions.Question;
import com.example.up804392.keystage3mathstutor.quiz.Questions.QuestionDifficulty;

import java.util.Optional;

public interface Quiz {

    Optional<Question> createQuestion(QuestionDifficulty difficulty);

}
