package com.example.up804392.keystage3mathstutor.quiz;

import com.example.up804392.keystage3mathstutor.quiz.Questions.Question;
import com.example.up804392.keystage3mathstutor.quiz.Questions.QuestionDifficulty;
import com.example.up804392.keystage3mathstutor.quiz.Questions.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AlgebraicTerms implements Quiz {

    private static final String QUESTION = "Simplify the following:\n";
    private Random RANDOM = new Random();

    public Optional<Question> createQuestion(QuestionDifficulty difficulty) {
        MathQuestion question;
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
            case HARD: {
                question = createHardQuestionUsingFormat();
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
        for (int x = 0; x < numberOfNeededValues; x++) {
            double value = RANDOM.nextInt(10) - RANDOM.nextInt(6);
            if (value == 0) {
                value++;
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
        value = value.replaceAll("^1x", "x");
        value = value.replaceAll("\\+1x", "+x");
        value = value.replaceAll("-1x", "-x");
        value = value.replaceAll("^0x[+]|^0x", "");
        value = value.replaceAll("\\+1y", "+y");
        value = value.replaceAll("-1y", "-y");
        value = value.replaceAll("[+|-]0[x|y]?", "");
        return value;
    }

    private MathQuestion createEasyQuestionUsingFormat1() {
        return new MathQuestion(QUESTION + "%.0fx %s %.0fy %s %.0fx %s %.0fy", 4, 3, (values) -> {
            if (values.size() == 4) {
                double x = values.get(0) + values.get(2);
                double y = values.get(1) + values.get(3);
                return regex(String.format("%.0fx%s%.0fy", x, y >= 0 ? "+" : "", y));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 4) {
                return Optional.of(String.format(format, values.get(0), operations.get(0), values.get(1), operations.get(1), values.get(2), operations.get(2), values.get(3)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createEasyQuestionUsingFormat2() {
        return new MathQuestion(QUESTION + "%.0fx^%.0f %s %.0f %s %.0fx %s %.0fx %s %.0f %s %.0fx^%.0f", 7, 5, (values) -> {
            if (values.size() == 7) {
                double x = values.get(2) + values.get(3);
                double x2 = values.get(0) + values.get(5);
                double constant = values.get(1) + values.get(4);
                return regex(String.format("%.0fx^%.0f%s%.0fx%s%.0f", x2, values.get(6), x >= 0 ? "+" : "", x, constant >= 0 ? "+" : "", constant));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 7) {
                return Optional.of(String.format(format, values.get(0), values.get(6), operations.get(0), values.get(1), operations.get(1), values.get(2), operations.get(2), values.get(3), operations.get(3), values.get(4), operations.get(4), values.get(5), values.get(6)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createEasyQuestionUsingFormat3() {
        return new MathQuestion(QUESTION + "%.0fx^%.0f %s %.0f %s %.0f %s %.0fx^%.0f", 5, 3, (values) -> {
            if (values.size() == 5) {
                double x2 = values.get(0) + values.get(3);
                double constant = values.get(1) + values.get(2);
                return regex(String.format("%.0fx^%.0f%s%.0f", x2, values.get(4), constant >= 0 ? "+" : "", constant));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 5) {
                return Optional.of(String.format(format, values.get(0), values.get(4), operations.get(0), values.get(1), operations.get(1), values.get(2), operations.get(2), values.get(3), values.get(4)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createMediumQuestionUsingFormat1() {
        return new MathQuestion(QUESTION + "%.0fx * %.0fxy", 2, 0, (values) -> {
            if (values.size() == 2) {
                double value = values.get(0) * values.get(1);
                return regex(String.format("%.0fx^2y", value));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 2) {
                return Optional.of(String.format(format, values.get(0), values.get(1)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createMediumQuestionUsingFormat2() {
        return new MathQuestion(QUESTION + "%.0fx^%.0f * %.0fx^%.0f", 4, 0, (values) -> {
            if (values.size() == 4) {
                double power = values.get(1) + values.get(3);
                double value = values.get(0) * values.get(2);
                return regex(String.format("%.0fx^%.0f", value, power));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 4) {
                return Optional.of(String.format(format, values.get(0), values.get(1), values.get(2), values.get(3)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createMediumQuestionUsingFormat3() {
        return new MathQuestion(QUESTION + "%.0fx(%.0fx%s%.0fy)", 3, 1, (values) -> {
            if (values.size() == 3) {
                double x = values.get(0) * values.get(1);
                double y = values.get(0) * values.get(2);
                return regex(String.format("%.0fx^2%s%.0fxy", x, y >= 0 ? "+" : "", y));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 3) {
                return Optional.of(String.format(format, values.get(0), values.get(1), operations.get(0), values.get(2)));
            }
            return Optional.empty();
        });
    }

    private MathQuestion createHardQuestionUsingFormat() {
        return new MathQuestion(QUESTION + "(x%s%.0f)(x%s%.0f)", 2, 2, (values) -> {
            if (values.size() == 2) {
                double x = values.get(0) + values.get(1);
                double constant = values.get(0) * values.get(1);
                return regex(String.format("x^2%s%.0fx%s%.0f", x >= 0 ? "+" : "", x, constant >= 0 ? "+" : "", constant));
            }
            return "";
        }, (format, values, operations) -> {
            if (values.size() == 2) {
                return Optional.of(String.format(format, operations.get(0), values.get(0), operations.get(1), values.get(1)));
            }
            return Optional.empty();
        });
    }

}
