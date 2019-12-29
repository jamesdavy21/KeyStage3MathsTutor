package com.example.up804392.keystage3mathstutor.quiz.Questions;

import java.util.List;
import java.util.Optional;

public interface Formatter {

    Optional<String> format(String format, List<Double> values, List<String> operations);

}
