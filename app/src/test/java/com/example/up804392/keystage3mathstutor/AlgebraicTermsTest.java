package com.example.up804392.keystage3mathstutor;

import com.example.up804392.keystage3mathstutor.quiz.AlgebraicTerms;
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
@PrepareForTest({AlgebraicTerms.class})
public class AlgebraicTermsTest {

    @Mock
    Random random;

    private static final String QUESTION = "Simplify the following:\n";

    @Test
    public void AlgebraicTermsEasyQuestionCreated() throws Exception {
        for (int x = 0; x <3; x++) {
            Quiz quiz = mockAlgebraicTerms(x);
            Optional<Question> question = quiz.createQuestion(QuestionDifficulty.EASY);
            assertTrue(String.format("Question format %s not present", x+1), question.isPresent());
        }
    }

    @Test
    public void AlgebraicTermsMediumQuestionCreated() throws Exception {
        for (int x = 0; x <3; x++) {
            Quiz quiz = mockAlgebraicTerms(x);
            Optional<Question> question = quiz.createQuestion(QuestionDifficulty.MEDIUM);
            assertTrue(String.format("Question format %s not present", x+1), question.isPresent());
        }
    }

    @Test
    public void AlgebraicTermsHardQuestionCreated() throws Exception {
        for (int x = 0; x <1; x++) {
            Quiz quiz = mockAlgebraicTerms(x);
            Optional<Question> question = quiz.createQuestion(QuestionDifficulty.HARD);
            assertTrue(String.format("Question format %s not present", x+1), question.isPresent());
        }
    }

    @Test
    public void ExpressionsEasyQuestion1SolveCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, 2d, 2d, 2d)),
                new ArrayList<>(Arrays.asList("+", "+", "+")), QuestionDifficulty.EASY);
        assertEquals("4x+4y", question.solveQuestion());
    }

    @Test
    public void ExpressionsEasyQuestion1FormattedCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, 2d, 2d, 2d)),
                new ArrayList<>(Arrays.asList("+", "+", "+")), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "2x + 2y + 2x + 2y", question.getQuestion().get());
    }

    @Test
    public void ExpressionsEasyQuestion2SolveCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, 2d, 2d, 2d, 2d, 2d, 2d)),
                new ArrayList<>(Arrays.asList("+", "+", "+", "+", "+")), QuestionDifficulty.EASY);
        assertEquals("4x^2+4x+4", question.solveQuestion());
    }

    @Test
    public void ExpressionsEasyQuestion2FormattedCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, 2d, 2d, 2d, 2d, 2d, 2d)), new ArrayList<>(Arrays.asList("+", "+", "+", "+", "+")), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "2x^2 + 2 + 2x + 2x + 2 + 2x^2", question.getQuestion().get());
    }

    @Test
    public void ExpressionsEasyQuestion3SolveCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, 2d, 2d, 2d, 2d)), new ArrayList<>(Arrays.asList("+", "+", "+")), QuestionDifficulty.EASY);
        assertEquals("4x^2+4", question.solveQuestion());
    }

    @Test
    public void ExpressionsEasyQuestion3FormattedCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, 2d, 2d, 2d, 2d)), new ArrayList<>(Arrays.asList("+", "+", "+")), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "2x^2 + 2 + 2 + 2x^2", question.getQuestion().get());
    }

    @Test
    public void ExpressionsMediumQuestion1SolveCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 2d)), Collections.emptyList(), QuestionDifficulty.MEDIUM);
        assertEquals("10x^2y", question.solveQuestion());
    }

    @Test
    public void ExpressionsMediumQuestion1FormattedCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 2d)),
                Collections.emptyList(), QuestionDifficulty.MEDIUM);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "5x * 2xy", question.getQuestion().get());
    }

    @Test
    public void ExpressionsMediumQuestion2SolveCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, -2d, 2d, 5d)),
                Collections.emptyList(), QuestionDifficulty.MEDIUM);
        assertEquals("4x^3", question.solveQuestion());
    }

    @Test
    public void ExpressionsMediumQuestion2FormattedCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, -2d, 2d, 5d)),
                Collections.emptyList(), QuestionDifficulty.MEDIUM);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "2x^-2 * 2x^5", question.getQuestion().get());
    }

    @Test
    public void ExpressionsMediumQuestion3SolveCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, 2d, -3d)), new ArrayList<>(Collections.singletonList("")), QuestionDifficulty.MEDIUM);
        assertEquals("4x^2-6xy", question.solveQuestion());
    }

    @Test
    public void ExpressionsMediumQuestion3FormattedCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(2d, 2d, -3d)), new ArrayList<>(Collections.singletonList("")), QuestionDifficulty.MEDIUM);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "2x(2x-3y)", question.getQuestion().get());
    }

    @Test
    public void ExpressionsHardQuestion1SolveCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(3d, -1d)), new ArrayList<>(Arrays.asList("+", "")), QuestionDifficulty.HARD);
        assertEquals("x^2+2x-3", question.solveQuestion());
    }

    @Test
    public void ExpressionsHardQuestion1FormattedCorrectly() throws Exception {
        Quiz quiz = mockAlgebraicTerms(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(3d, -1d)), new ArrayList<>(Arrays.asList("+", "")), QuestionDifficulty.HARD);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "(x+3)(x-1)", question.getQuestion().get());
    }


    private Quiz mockAlgebraicTerms(int questionFormatNumber) throws Exception {
        PowerMockito.whenNew(Random.class).withAnyArguments().thenReturn(random);
        PowerMockito.whenNew(Random.class).withNoArguments().thenReturn(random);
        when(random.nextInt(anyInt())).thenReturn(questionFormatNumber);
        return new AlgebraicTerms();
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