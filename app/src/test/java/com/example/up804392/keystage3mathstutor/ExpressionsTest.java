package com.example.up804392.keystage3mathstutor;

import com.example.up804392.keystage3mathstutor.quiz.Expressions;
import com.example.up804392.keystage3mathstutor.quiz.MathQuestion;
import com.example.up804392.keystage3mathstutor.quiz.Questions.Question;
import com.example.up804392.keystage3mathstutor.quiz.Questions.QuestionDifficulty;
import com.example.up804392.keystage3mathstutor.quiz.Questions.Quiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Expressions.class})
public class ExpressionsTest {

    private static final String QUESTION = "Solve the following for x:\n";

    @Mock
    Random random;

    @Test
    public void ExpressionsEasyQuestionCreated() throws Exception {
        for (int x = 0; x <3; x++) {
            Quiz quiz = mockExpressions(x);
            Optional<Question> question = quiz.createQuestion(QuestionDifficulty.EASY);
            assertTrue(String.format("Question format %s not present", x+1), question.isPresent());
        }
    }

    @Test
    public void ExpressionsMediumQuestionCreated() throws Exception {
        for (int x = 0; x <3; x++) {
            Quiz quiz = mockExpressions(x);
            Optional<Question> question = quiz.createQuestion(QuestionDifficulty.MEDIUM);
            assertTrue(String.format("Question format %s not present", x+1), question.isPresent());
        }
    }

    @Test
    public void ExpressionsEasyQuestion1SolveCorrectly() throws Exception {
        Quiz quiz = mockExpressions(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 2d)), Collections.emptyList(), QuestionDifficulty.EASY);
        assertEquals("0.4", question.solveQuestion());
    }

    @Test
    public void ExpressionsEasyQuestion1FormattedCorrectly() throws Exception {
        Quiz quiz = mockExpressions(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 2d)),Collections.emptyList(), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "5x=2", question.getQuestion().get());
    }

    @Test
    public void ExpressionsEasyQuestion2SolveCorrectly() throws Exception {
        Quiz quiz = mockExpressions(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(-2d, 2d)),Collections.singletonList("+"), QuestionDifficulty.EASY);
        assertEquals("4.0", question.solveQuestion());
    }

    @Test
    public void ExpressionsEasyQuestion2FormattedCorrectly() throws Exception {
        Quiz quiz = mockExpressions(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(-2d, 2d)),Collections.singletonList(""), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "x-2=2", question.getQuestion().get());
    }

    @Test
    public void ExpressionsEasyQuestion3SolveCorrectly() throws Exception {
        Quiz quiz = mockExpressions(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, -2d, 10d)),Collections.singletonList("-"), QuestionDifficulty.EASY);
        assertEquals("6.0", question.solveQuestion());
    }

    @Test
    public void ExpressionsEasyQuestion3FormattedCorrectly() throws Exception {
        Quiz quiz = mockExpressions(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, -2d, 10d)),Collections.singletonList(""), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "2x-2=10", question.getQuestion().get());
    }

    @Test
    public void ExpressionsMediumQuestion1SolveCorrectly() throws Exception {
        Quiz quiz = mockExpressions(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 2d, 10d)), Collections.singletonList("+"), QuestionDifficulty.MEDIUM);
        assertEquals("2.5", question.solveQuestion());
    }

    @Test
    public void ExpressionsMediumQuestion1FormattedCorrectly() throws Exception {
        Quiz quiz = mockExpressions(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 2d, 10d)),Collections.singletonList("+"), QuestionDifficulty.MEDIUM);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "5+2x=10", question.getQuestion().get());
    }

    @Test
    public void ExpressionsMediumQuestion2SolveCorrectly() throws Exception {
        Quiz quiz = mockExpressions(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(-2d, 2d, 2d)),Collections.singletonList("+"), QuestionDifficulty.MEDIUM);
        assertEquals("0.5", question.solveQuestion());
    }

    @Test
    public void ExpressionsMediumQuestion2FormattedCorrectly() throws Exception {
        Quiz quiz = mockExpressions(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(-2d, 2d, 2d)),Collections.singletonList("+"), QuestionDifficulty.MEDIUM);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "-2x+2=2x", question.getQuestion().get());
    }

    @Test
    public void ExpressionsMediumQuestion3SolveCorrectly() throws Exception {
        Quiz quiz = mockExpressions(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 5d, 10d, 3d)), new ArrayList<>(Arrays.asList("+", "+")), QuestionDifficulty.MEDIUM);
        assertEquals("2.5", question.solveQuestion());
    }

    @Test
    public void ExpressionsMediumQuestion3FormattedCorrectly() throws Exception {
        Quiz quiz = mockExpressions(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 5d, 10d, 3d)), new ArrayList<>(Arrays.asList("+", "+")), QuestionDifficulty.MEDIUM);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "5x+5=10+3x", question.getQuestion().get());
    }


    private Quiz mockExpressions(int questionFormatNumber) throws Exception {
        PowerMockito.whenNew(Random.class).withAnyArguments().thenReturn(random);
        PowerMockito.whenNew(Random.class).withNoArguments().thenReturn(random);
        when(random.nextInt(anyInt())).thenReturn(questionFormatNumber);
        return new Expressions();
    }

    private Question createQuestionAndSetValuesForTest(Quiz quiz, List<Double> values, List<String> operations, QuestionDifficulty difficulty) {
        Optional<Question> question = quiz.createQuestion(difficulty);
        assertTrue(question.isPresent());
        MathQuestion mathQuestion = (MathQuestion) question.get();
        mathQuestion.setValues(values);
        mathQuestion.setOperations(operations);
        return mathQuestion;
    }
}