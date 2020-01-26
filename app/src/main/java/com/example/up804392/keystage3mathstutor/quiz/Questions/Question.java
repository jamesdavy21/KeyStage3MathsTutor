package com.example.up804392.keystage3mathstutor.quiz.Questions;

import java.util.List;
import java.util.Optional;

public class Question {

    private String format;
    private int numbersNeeded;
    private int operationsNeeded;
    private Solver solver;
    private Formatter formatter;
    private List<Double> values;
    private List<String> operations;

    public Question(String format, int numbersNeeded, int operationsNeeded, Solver solver, Formatter formatter) {
        this.format = format;
        this.numbersNeeded = numbersNeeded;
        this.operationsNeeded = operationsNeeded;
        this.solver = solver;
        this.formatter = formatter;
    }

    public Optional<String> solveQuestion() {
        return solver.solve(values);
    }

    public Optional<String> getQuestion() {
        return formatter.format(format, values, operations);
    }

    public int getNumbersNeeded() {
        return numbersNeeded;
    }

    public int getOperationsNeeded() {
        return operationsNeeded;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public void setOperations(List<String> operations) {
        this.operations = operations;
    }

    public boolean isAnswerCorrect(double value) {
        if (solveQuestion().isPresent()) {
            return value == round(Double.valueOf(solveQuestion().get()));
        }
        return false;
    }

    public boolean isAnswerCorrect(String  value) {
        return value.replaceAll("\\s+","").equals(solveQuestion().orElse("error"));
    }

    public static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
