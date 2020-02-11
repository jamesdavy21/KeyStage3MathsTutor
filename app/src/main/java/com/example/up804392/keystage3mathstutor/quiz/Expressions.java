package com.example.up804392.keystage3mathstutor.quiz;



import com.example.up804392.keystage3mathstutor.quiz.Questions.Question;
import com.example.up804392.keystage3mathstutor.quiz.Questions.QuestionDifficulty;

import java.util.*;

public class Expressions implements Quiz{

    private static final String QUESTION = "Solve the following for x:\n";
    private static final Random RANDOM = new Random();

    public Optional<Question> createQuestion(QuestionDifficulty difficulty) {
        Question question;
        switch (difficulty) {
            case EASY: {
                switch (RANDOM.nextInt(3) + 1) {
                    case 1: {
                        question = createEasyQuestionUsingFormat1();
                        break;
                    }
                    case 2: {
                        question = createEasyQuestionUsingFormat2();
                        break;
                    }
                    default: {
                        question = createEasyQuestionUsingFormat3();
                        break;
                    }
                }
                break;
            }
            case MEDIUM: {
                switch (RANDOM.nextInt(3) + 1) {
                    case 1: {
                        question = createMediumQuestionUsingFormat1();
                        break;
                    }
                    case 2: {
                        question = createMediumQuestionUsingFormat2();
                        break;
                    }
                    default: {
                        question = createMediumQuestionUsingFormat3();
                        break;
                    }
                }
                break;
            }
            default: {
                return Optional.empty();
            }
        }
        List<Double> values = getValuesForQuestion(question.getNumbersNeeded());
        question.setValues(values);
        question.setOperations(getOperationsForQuestion(question.getOperationsNeeded(), values));
        return Optional.of(question);
    }

    private List<Double> getValuesForQuestion(int numberOfNeededValues) {
        List<Double> values = new ArrayList<>();
        for(int x = 0; x < numberOfNeededValues; x++) {
            double value =  RANDOM.nextInt(10) - RANDOM.nextInt(6);
            if (value == 0) {
                value++;
            }
            values.add(value);
        }
        return values;
    }

    private List<String> getOperationsForQuestion(int numberOfNeededOperations, List<Double> values) {
        List<String> operations = new ArrayList<>();
        for(int x = 1; x <= numberOfNeededOperations; x++) {
            if (x == 1 && values.size() == 2 && values.get(0) > 0) {
                operations.add("+");
            } else if (x == 1 && (values.size() == 3 || values.size() == 4) && values.get(1) > 0) {
                operations.add("+");
            } else if (x == 2 && values.size() == 4 && values.get(3) > 0) {
                operations.add("+");
            } else {
                operations.add("");
            }
        }
        return operations;
    }

    private Question createEasyQuestionUsingFormat1() {
        return new Question(QUESTION + "%.0fx=%.0f", 2, 0, (values) -> {
            if (values.size() == 2) {
                return Optional.of(String.valueOf(values.get(1) / values.get(0)));
            }
            return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 2) {
                return Optional.of(String.format(format, values.get(0), values.get(1)));
            }return Optional.empty();
        });
    }

    private Question createEasyQuestionUsingFormat2() {
        return new Question(QUESTION + "x%s%.0f=%.0f", 2, 1, (values) -> {
            if (values.size() == 2) {
                return Optional.of(String.valueOf(values.get(1) - values.get(0)));
            }
            return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 2 && operations.size() == 1) {
                return Optional.of(String.format(format, operations.get(0), values.get(0), values.get(1)));
            }return Optional.empty();
        });
    }

    private Question createEasyQuestionUsingFormat3() {
        return new Question(QUESTION + "%.0fx%s%.0f=%.0f", 3, 1, (values) -> {
            if (values.size() == 3) {
                return Optional.of(String.valueOf((values.get(2) - values.get(1)) / values.get(0)));
            }return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 3 && operations.size() == 1) {
                return Optional.of(String.format(format, values.get(0), operations.get(0), values.get(1), values.get(2)));
            }return Optional.empty();
        });
    }

    private Question createMediumQuestionUsingFormat1() {
        return new Question(QUESTION + "%.0f%s%.0fx=%.0f", 3, 1, (values) -> {
            if (values.size() == 3) {
                return Optional.of(String.valueOf((values.get(2) - values.get(0)) / values.get(1)));
            }
            return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 3 && operations.size() == 1) {
                return Optional.of(String.format(format, values.get(0), operations.get(0), values.get(1), values.get(2)));
            }return Optional.empty();
        });
    }

    private Question createMediumQuestionUsingFormat2() {
        return new Question(QUESTION + "%.0fx%s%.0f=%.0fx", 3, 1, (values) -> {
            if (values.size() == 3) {
                return Optional.of(String.valueOf((-values.get(1)) / (values.get(0) - values.get(2))));
            }
            return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 3 && operations.size() == 1) {
                return Optional.of(String.format(format, values.get(0), operations.get(0) , values.get(1), values.get(2)));
            }return Optional.empty();
        });
    }

    private Question createMediumQuestionUsingFormat3() {
        return new Question(QUESTION + "%.0fx%s%.0f=%.0f%s%.0fx", 4, 2, (values) -> {
            if (values.size() == 4) {
                return Optional.of(String.valueOf((values.get(2) - values.get(1)) / (values.get(0) - values.get(3))));
            }
            return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 4 && operations.size() == 2) {
                return Optional.of(String.format(format, values.get(0), operations.get(0) , values.get(1), values.get(2), operations.get(1), values.get(3)));
            }return Optional.empty();
        });
    }
}
