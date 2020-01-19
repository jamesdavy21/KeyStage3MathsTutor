package com.example.up804392.keystage3mathstutor.quiz;



import com.example.up804392.keystage3mathstutor.quiz.Questions.Question;
import com.example.up804392.keystage3mathstutor.quiz.Questions.QuestionDifficulty;

import java.util.*;

public class Expressions {

    private static final Random RANDOM = new Random();

    public Optional<Question> createQuestion(QuestionDifficulty difficulty) {
        int questFormatNam = RANDOM.nextInt(5);
        Question question;
        switch (difficulty) {
            case MEDIUM: {
                switch (questFormatNam + 1) {
                    case 2: {
                        question = createMediumQuestionUsingFormat2();
                        break;
                    }
                    case 3: {
                        question = createMediumQuestionUsingFormat3();
                        break;
                    }
                    case 4: {
                        question = createMediumQuestionUsingFormat4();
                        break;
                    }
                    case 5: {
                        question = createMediumQuestionUsingFormat5();
                        break;
                    }
                    default: {
                        question = createMediumQuestionUsingFormat1();
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

    private Question createMediumQuestionUsingFormat1() {
        return new Question("%.0fx=%.0f", 2, 0, (values) -> {
            if (values.size() == 2) {
                return Optional.of(values.get(1) / values.get(0));
            }
            return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 2) {
                return Optional.of(String.format(format, values.get(0), values.get(1)));
            }return Optional.empty();
        });
    }

    private Question createMediumQuestionUsingFormat2() {
        return new Question("x%s%.0f=%.0f", 2, 1, (values) -> {
            if (values.size() == 2) {
                return Optional.of(values.get(1) - values.get(0));
            }
            return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 2 && operations.size() == 1) {
                return Optional.of(String.format(format, operations.get(0), values.get(0), values.get(1)));
            }return Optional.empty();
        });
    }

    private Question createMediumQuestionUsingFormat3() {
        return new Question("%.0fx%s%.0f=%.0f", 3, 1, (values) -> {
            if (values.size() == 3) {
                return Optional.of((values.get(2) - values.get(1)) / values.get(0));
            }return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 3 && operations.size() == 1) {
                return Optional.of(String.format(format, values.get(0), operations.get(0), values.get(1), values.get(2)));
            }return Optional.empty();
        });
    }

    private Question createMediumQuestionUsingFormat4() {
        return new Question("%.0f%s%.0fx=%.0f", 3, 1, (values) -> {
            if (values.size() == 3) {
                return Optional.of((values.get(2) - values.get(0)) / values.get(1));
            }
            return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 3 && operations.size() == 1) {
                return Optional.of(String.format(format, values.get(0), operations.get(0), values.get(1), values.get(2)));
            }return Optional.empty();
        });
    }

    private Question createMediumQuestionUsingFormat5() {
        return new Question("%.0fx%s%.0f=%.0f%s%.0fx", 4, 2, (values) -> {
            if (values.size() == 4) {
                return Optional.of((values.get(2) - values.get(1)) / (values.get(0) - values.get(3)));
            }
            return Optional.empty();
        }, (format, values, operations) -> {
            if (values.size() == 4 && operations.size() == 2) {
                return Optional.of(String.format(format, values.get(0), operations.get(0) , values.get(1), values.get(2), operations.get(1), values.get(3)));
            }return Optional.empty();
        });
    }
}
