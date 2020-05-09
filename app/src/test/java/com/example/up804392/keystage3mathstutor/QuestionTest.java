package com.example.up804392.keystage3mathstutor;

import com.example.up804392.keystage3mathstutor.quiz.Questions.Question;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public class QuestionTest implements Serializable {

    @Test
    public void TestStringBasedQuestion() {
        Question question = new Question() {
            @Override
            public <T> T solveQuestion() {
                return null;
            }

            @Override
            public <T> Optional<T> getQuestion() {
                return (Optional<T>) Optional.empty();
            }

            @Override
            public <T> boolean isAnswerCorrect(T value) {
                return value.equals("test");
            }
        };

        Assert.assertTrue(question.isAnswerCorrect("test"));

    }

    @Test
    public void TestIntegerBasedQuestion() {
        Question question = new Question() {
            @Override
            public <T> T solveQuestion() {
                return null;
            }

            @Override
            public <T> Optional<T> getQuestion() {
                return Optional.empty();
            }

            @Override
            public <T> boolean isAnswerCorrect(T value) {
                return value.equals(1);
            }
        };

        Assert.assertTrue(question.isAnswerCorrect(1));

    }

    @Test
    public void TestObjectBasedQuestion() throws IOException {
        Question question = new Question() {
            @Override
            public testClass solveQuestion() {
                return new testClass(1);
            }

            @Override
            public <T> Optional<T> getQuestion() {
                return Optional.empty();
            }

            @Override
            public <T> boolean isAnswerCorrect(T value) {
                return value.equals(solveQuestion());
            }
        };
        testClass testAnswer = new testClass(1);
        Assert.assertTrue(question.isAnswerCorrect(testAnswer));

    }

    class testClass implements Serializable {
        private int value;

        public testClass(int value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            testClass test = (testClass) o;
            return value == test.value;
        }
    }
}
