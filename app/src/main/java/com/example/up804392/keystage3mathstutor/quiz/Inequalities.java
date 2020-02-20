package com.example.up804392.keystage3mathstutor.quiz;

import com.example.up804392.keystage3mathstutor.quiz.Questions.Question;
import com.example.up804392.keystage3mathstutor.quiz.Questions.QuestionDifficulty;
import com.example.up804392.keystage3mathstutor.quiz.Questions.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Inequalities implements Quiz {

    private static final String QUESTION = "What values can x be to\n satisfy the following inequality:\n";
    private static final String QUESTION_HARD = "What values can x be to\n satisfy the following inequalitys:\n";
    private Random RANDOM = new Random();

    public Optional<Question> createQuestion(QuestionDifficulty difficulty) {
        MathQuestion question;
        switch (difficulty) {
            case EASY: {
                switch (RANDOM.nextInt(4) + 1) {
                    case 1: {
                        question = createEasyQuestionUsingFormat1();
                        break;
                    }
                    case 2: {
                        question = createEasyQuestionUsingFormat2();
                        break;
                    }
                    case 3: {
                        question = createEasyQuestionUsingFormat3();
                        break;
                    }
                    default: {
                        question = createEasyQuestionUsingFormat4();
                        break;
                    }
                }
                break;
            }
            case MEDIUM: {
//                switch (RANDOM.nextInt(3) + 1) {
//                    case 1: {
//                        question = createMediumQuestionUsingFormat1();
//                        break;
//                    }
//                    case 2: {
//                        question = createMediumQuestionUsingFormat2();
//                        break;
//                    }
//                    default: {
//                        question = createMediumQuestionUsingFormat3();
//                        break;
//                    }
//                }
//                break;
                return Optional.empty();
            }
            case HARD: {
                switch (RANDOM.nextInt(4) + 1) {
                    case 1: {
                        question = createHardQuestionUsingFormat1();
                        break;
                    }
                    case 2: {
                        question = createHardQuestionUsingFormat2();
                        break;
                    }
                    case 3: {
                        question = createHardQuestionUsingFormat3();
                        break;
                    }
                    default: {
                        question = createHardQuestionUsingFormat4();
                        break;
                    }
                }
                break;
            }
            default: {
                return Optional.empty();
            }
        }
        List<Double> values = getValuesForQuestion(question.getNumbersNeeded(), difficulty);
        question.setValues(values);
        question.setOperations(getOperationsForQuestion(question.getOperationsNeeded(), values));
        return Optional.of(question);
    }

    private List<Double> getValuesForQuestion(int numberOfNeededValues, QuestionDifficulty difficulty) {
        List<Double> values = new ArrayList<>();
        double value;
        for (int x = 0; x < numberOfNeededValues; x++) {
            if (difficulty == QuestionDifficulty.HARD && (x == 0 || x == 3)) {
                value = RANDOM.nextInt(5) + 6;
            } else if (difficulty == QuestionDifficulty.HARD && (x == 1 || x == 2)) {
                value = RANDOM.nextInt(5);
            } else {
                value = RANDOM.nextInt(10);
            }

            if (value == 0) {
                value = 2;
            } else if (value % 2 != 0) {
                if (value == -1) {
                    value--;
                } else {
                    value++;
                }
            }
            values.add(value);
        }
        return values;
    }

    private List<String> getOperationsForQuestion(int numberOfNeededOperations, List<Double> values) {
        List<String> operations = new ArrayList<>();
        for (int x = 1; x <= numberOfNeededOperations; x++) {
            if (values.size() == 2) {
                if (values.get(x - 1) > 0) {
                    operations.add("+");
                } else {
                    operations.add("");
                }
            } else if (values.get(x) > 0) {
                operations.add("+");
            } else {
                operations.add("");
            }
        }
        return operations;
    }

    private String regex(String value) {
        value = value.replaceAll("\\.00", "");
        value = value.replaceFirst("(\\.[0-9])(0)", "$1");
        value = value.replaceFirst("-0$", "0");
        return value;
    }

    private MathQuestion createEasyQuestionUsingFormat1() {
        return new MathQuestion(QUESTION + "%.0fx %s %.0f < %.0f", 3, 1, (values) -> {
            if (values.size() == 3) {
                double constant = (values.get(2) - values.get(1)) / values.get(0);
                return regex(String.format("x<%.2f", constant));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 3) {
                return Optional.of(String.format(format, values.get(0), operations.get(0), values.get(1), values.get(2)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createEasyQuestionUsingFormat2() {
        return new MathQuestion(QUESTION + "%.0f %s %.0fx > %.0f", 3, 1, (values) -> {
            if (values.size() == 3) {
                double constant = (values.get(2) - values.get(0)) / values.get(1);
                return regex(String.format("x>%.2f", constant));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 3) {
                return Optional.of(String.format(format, values.get(0), operations.get(0), values.get(1), values.get(2)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createEasyQuestionUsingFormat3() {
        return new MathQuestion(QUESTION + "%.0f %s %.0fx <= %.0f", 3, 1, (values) -> {
            if (values.size() == 3) {
                double constant = (values.get(2) - values.get(0)) / values.get(1);
                return regex(String.format("x<=%.2f", constant));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 3) {
                return Optional.of(String.format(format, values.get(0), operations.get(0), values.get(1), values.get(2)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createEasyQuestionUsingFormat4() {
        return new MathQuestion(QUESTION + "%.0fx %s %.0f >= %.0f", 3, 1, (values) -> {
            if (values.size() == 3) {
                double constant = (values.get(2) - values.get(1)) / values.get(0);
                return regex(String.format("x>=%.2f", constant));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 3) {
                return Optional.of(String.format(format, values.get(0), operations.get(0), values.get(1), values.get(2)));
            }
            return Optional.empty();
        });
    }

    //add medium questions

    private MathQuestion createHardQuestionUsingFormat1() {
        return new MathQuestion(QUESTION_HARD + "x %s %.0f > %.0f and x %s %.0f < %.0f", 4, 2, (values) -> {
            if (values.size() == 4) {
                double greater = values.get(1) - values.get(0);
                double lesser = values.get(3) - values.get(2);
                return regex(String.format("%.0f<x<%.0f", greater, lesser));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 4) {
                return Optional.of(String.format(format, operations.get(0), values.get(0), values.get(1), operations.get(1), values.get(2), values.get(3)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createHardQuestionUsingFormat2() {
        return new MathQuestion(QUESTION_HARD + "x %s %.0f >= %.0f and x %s %.0f < %.0f", 4, 2, (values) -> {
            if (values.size() == 4) {
                double greater = values.get(1) - values.get(0);
                double lesser = values.get(3) - values.get(2);
                return regex(String.format("%.0f<=x<%.0f", greater, lesser));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 4) {
                return Optional.of(String.format(format, operations.get(0), values.get(0), values.get(1), operations.get(1), values.get(2), values.get(3)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createHardQuestionUsingFormat3() {
        return new MathQuestion(QUESTION_HARD + "x %s %.0f > %.0f and x %s %.0f <= %.0f", 4, 2, (values) -> {
            if (values.size() == 4) {
                double greater = values.get(1) - values.get(0);
                double lesser = values.get(3) - values.get(2);
                return regex(String.format("%.0f<x<=%.0f", greater, lesser));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 4) {
                return Optional.of(String.format(format, operations.get(0), values.get(0), values.get(1), operations.get(1), values.get(2), values.get(3)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createHardQuestionUsingFormat4() {
        return new MathQuestion(QUESTION_HARD + "x %s %.0f >= %.0f and x %s %.0f <= %.0f", 4, 2, (values) -> {
            if (values.size() == 4) {
                double greater = values.get(1) - values.get(0);
                double lesser = values.get(3) - values.get(2);
                return regex(String.format("%.0f<=x<=%.0f", greater, lesser));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 4) {
                return Optional.of(String.format(format, operations.get(0), values.get(0), values.get(1), operations.get(1), values.get(2), values.get(3)));
            }
            return Optional.empty();
        });
    }

}
