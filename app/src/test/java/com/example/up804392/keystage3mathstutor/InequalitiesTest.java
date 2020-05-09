package com.example.up804392.keystage3mathstutor;

import com.example.up804392.keystage3mathstutor.quiz.Inequalities;
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
@PrepareForTest({Inequalities.class})
public class InequalitiesTest {

    private static final String QUESTION = "What values can x be to\n satisfy the following inequality:\n";
    private static final String QUESTION_HARD = "What values can x be to\n satisfy the following inequalities:\n";

    @Mock
    Random random;

    @Test
    public void InequalitiesEasyQuestionCreated() throws Exception {
        for (int x = 0; x <4; x++) {
            Quiz quiz = mockInequalities(x);
            Optional<Question> question = quiz.createQuestion(QuestionDifficulty.EASY);
            assertTrue(String.format("Question format %s not present", x+1), question.isPresent());
        }
    }

    @Test
    public void InequalitiesHardQuestionCreated() throws Exception {
        for (int x = 0; x <4; x++) {
            Quiz quiz = mockInequalities(x);
            Optional<Question> question = quiz.createQuestion(QuestionDifficulty.HARD);
            assertTrue(String.format("Question format %s not present", x+1), question.isPresent());
        }
    }

    @Test
    public void InequalitiesEasyQuestion1SolveCorrectly() throws Exception {
        Quiz quiz = mockInequalities(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 2d, 12d)),Collections.singletonList("+"), QuestionDifficulty.EASY);
        assertEquals("x<2", question.solveQuestion());
    }

    @Test
    public void InequalitiesEasyQuestion1FormattedCorrectly() throws Exception {
        Quiz quiz = mockInequalities(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(5d, 2d, 12d)),Collections.singletonList("+"), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "5x + 2 < 12", question.getQuestion().get());
    }

    @Test
    public void InequalitiesEasyQuestion2SolveCorrectly() throws Exception {
        Quiz quiz = mockInequalities(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(-2d, 2d, 6d)),Collections.singletonList("+"), QuestionDifficulty.EASY);
        assertEquals("x>4", question.solveQuestion());
    }

    @Test
    public void InequalitiesEasyQuestion2FormattedCorrectly() throws Exception {
        Quiz quiz = mockInequalities(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(-2d, 2d, 6d)),Collections.singletonList("+"), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "-2 + 2x > 6", question.getQuestion().get());
    }

    @Test
    public void InequalitiesEasyQuestion3SolveCorrectly() throws Exception {
        Quiz quiz = mockInequalities(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(1d, -3d, 10d)),Collections.singletonList("-"), QuestionDifficulty.EASY);
        assertEquals("x<=-3", question.solveQuestion());
    }

    @Test
    public void InequalitiesEasyQuestion3FormattedCorrectly() throws Exception {
        Quiz quiz = mockInequalities(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(1d, -3d, 10d)),Collections.singletonList("-"), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "1 - -3x <= 10", question.getQuestion().get());
    }

    @Test
    public void InequalitiesEasyQuestion4SolveCorrectly() throws Exception {
        Quiz quiz = mockInequalities(3);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(1d, 5d, 9d)),Collections.singletonList("-"), QuestionDifficulty.EASY);
        assertEquals("x>=4", question.solveQuestion());
    }

    @Test
    public void InequalitiesEasyQuestion4FormattedCorrectly() throws Exception {
        Quiz quiz = mockInequalities(3);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(1d, 5d, 9d)),Collections.singletonList("-"), QuestionDifficulty.EASY);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION + "1x - 5 >= 9", question.getQuestion().get());
    }

    @Test
    public void InequalitiesHardQuestion1SolveCorrectly() throws Exception {
        Quiz quiz = mockInequalities(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(4d, 5d, 4d, 6d)),Collections.singletonList("+"), QuestionDifficulty.HARD);
        assertEquals("1<x<2", question.solveQuestion());
    }

    @Test
    public void InequalitiesHardQuestion1FormattedCorrectly() throws Exception {
        Quiz quiz = mockInequalities(0);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(4d, 5d, 4d, 6d)), new ArrayList<>(Arrays.asList("+", "+")), QuestionDifficulty.HARD);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION_HARD + "x + 4 > 5 and x + 4 < 6", question.getQuestion().get());
    }

    @Test
    public void InequalitiesHardQuestion2SolveCorrectly() throws Exception {
        Quiz quiz = mockInequalities(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(3d, 2d, 6d, 7d)),Collections.singletonList("+"), QuestionDifficulty.HARD);
        assertEquals("-1<=x<1", question.solveQuestion());
    }

    @Test
    public void InequalitiesHardQuestion2FormattedCorrectly() throws Exception {
        Quiz quiz = mockInequalities(1);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(3d, 2d, 6d, 7d)), new ArrayList<>(Arrays.asList("+", "+")), QuestionDifficulty.HARD);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION_HARD + "x + 3 >= 2 and x + 6 < 7", question.getQuestion().get());
    }

    @Test
    public void InequalitiesHardQuestion3SolveCorrectly() throws Exception {
        Quiz quiz = mockInequalities(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(1d, -1d, -4d, -2d)),Collections.singletonList("-"), QuestionDifficulty.HARD);
        assertEquals("-2<x<=2", question.solveQuestion());
    }

    @Test
    public void InequalitiesHardQuestion3FormattedCorrectly() throws Exception {
        Quiz quiz = mockInequalities(2);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(1d, -1d, -4d, -2d)), new ArrayList<>(Arrays.asList("+", "")), QuestionDifficulty.HARD);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION_HARD + "x + 1 > -1 and x  -4 <= -2", question.getQuestion().get());
    }

    @Test
    public void InequalitiesHardQuestion4SolveCorrectly() throws Exception {
        Quiz quiz = mockInequalities(3);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(-5d, -15d, 9d, 9d)),Collections.singletonList("-"), QuestionDifficulty.HARD);
        assertEquals("-10<=x<=0", question.solveQuestion());
    }

    @Test
    public void InequalitiesHardQuestion4FormattedCorrectly() throws Exception {
        Quiz quiz = mockInequalities(3);
        Question question = createQuestionAndSetValuesForTest(quiz, new ArrayList<>(Arrays.asList(-5d, -15d, 9d, 9d)), new ArrayList<>(Arrays.asList("", "+")), QuestionDifficulty.HARD);
        assertTrue(question.getQuestion().isPresent());
        assertEquals(QUESTION_HARD + "x  -5 >= -15 and x + 9 <= 9", question.getQuestion().get());
    }



    private  Quiz mockInequalities(int questionFormatNumber) throws Exception {
        PowerMockito.whenNew(Random.class).withAnyArguments().thenReturn(random);
        PowerMockito.whenNew(Random.class).withNoArguments().thenReturn(random);
        when(random.nextInt(anyInt())).thenReturn(questionFormatNumber);
        return new Inequalities();
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