package com.example.up804392.keystage3mathstutor.quiz;

import com.example.up804392.keystage3mathstutor.quiz.Questions.Formatter;
import com.example.up804392.keystage3mathstutor.quiz.Questions.Question;
import com.example.up804392.keystage3mathstutor.quiz.Questions.Solver;

import java.util.List;
import java.util.Optional;

public class MathQuestion implements Question {

    private String format;
    private int numbersNeeded;
    private int operationsNeeded;
    private Solver solver;
    private Formatter formatter;
    private List<Double> values;
    private List<String> operations;

    public MathQuestion(String format, int numbersNeeded, int operationsNeeded, Solver solver, Formatter formatter) {
        this.format = format;
        this.numbersNeeded = numbersNeeded;
        this.operationsNeeded = operationsNeeded;
        this.solver = solver;
        this.formatter = formatter;
    }

    @Override
    public String solveQuestion() {
        return String.valueOf(solver.solve(values));
    }

    @Override
    public Optional<String> getQuestion() {
        return formatter.format(format, values, operations);
    }

    @Override
    public <T> boolean isAnswerCorrect(T value) {
        return regex((String) value).equals(regex(solveQuestion()));
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

    private String regex(String value) {
        value = value.replaceAll("\\s+","");
        value = value.replaceAll("\\s-0","0");
        value = value.replaceAll("\\.0$", "");
        return value;
    }

    public static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
