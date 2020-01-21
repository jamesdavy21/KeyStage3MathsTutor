package com.example.up804392.keystage3mathstutor.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.up804392.keystage3mathstutor.MainActivity;
import com.example.up804392.keystage3mathstutor.R;
import com.example.up804392.keystage3mathstutor.quiz.Expressions;
import com.example.up804392.keystage3mathstutor.quiz.Questions.Question;
import com.example.up804392.keystage3mathstutor.quiz.Questions.QuestionDifficulty;
import com.example.up804392.keystage3mathstutor.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {

    private static final String TOPIC = "TOPIC";
    private static final String DIFFICULTY = "DIFFICULTY";
    private static final String SCORE = "SCORE";
    private static final String EQUATION = "Equations";
    private static final int MAX_NUMBER_OF_QUESTIONS = 10;

    private MainActivity activity;
    private EditText answerCentre;
    private EditText answerLeft;
    private EditText answerRight;
    private TextView fractionSeparator;
    private TextView questionTextView;
    private TextView questionNumber;
    private TextView userAnswerTextView;
    private TextView correctAnswerTextView;
    private TextView responseTextView;
    private Button nextButton;
    private Button previousButton;
    private Button checkAnswerButton;
    private Switch fractionSwitch;
    private ConstraintLayout answerPopup;
    private int currentQuestion = 1;
    private String topic;
    private String difficulty;

    private List<Question> questions;
    private String[] singleAnswers;
    private String[][] multiAnswers;
    private boolean[] questionAnswered;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = getViewAndSetVariables(inflater, container);
        singleAnswers = new String[10];
        multiAnswers = new String[10][2];
        questionAnswered = new boolean[10];

        startQuiz();

        activity.setToolbarTitle(topic + " quiz - " + difficulty);

        nextButton.setOnClickListener(this::onNextButtonClick);

        previousButton.setOnClickListener(this::onPreviousButtonClick);

        checkAnswerButton.setOnClickListener(this::onCheckAnswerButtonClick);

        fractionSwitch.setOnClickListener(this::onFractionSwitchClick);

        activity.uncheckCurrentCheckedItemInNavigationView();

        return view;
    }

    private View getViewAndSetVariables(@NonNull LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        activity = (MainActivity) getActivity();

        answerCentre = view.findViewById(R.id.editText_answer);
        answerLeft = view.findViewById(R.id.editText_left);
        answerRight = view.findViewById(R.id.editText_right);

        fractionSeparator = view.findViewById(R.id.textView_separator);
        questionTextView = view.findViewById(R.id.textView_question);
        questionNumber = view.findViewById(R.id.textView_question_number);

        nextButton = view.findViewById(R.id.button_next);
        previousButton = view.findViewById(R.id.button_previous);
        checkAnswerButton = view.findViewById(R.id.button_check);

        fractionSwitch = view.findViewById(R.id.switch_fraction);

        answerPopup = view.findViewById(R.id.constraint_layout_popup);

        userAnswerTextView = answerPopup.findViewById(R.id.textView_user_answer);
        correctAnswerTextView = answerPopup.findViewById(R.id.textView_correct_answer);
        responseTextView = answerPopup.findViewById(R.id.textView_answer_response);

        return view;
    }

    private void startQuiz() {
        topic = getArguments() != null ? getArguments().getString(TOPIC) : null;
        difficulty = getArguments() != null ? getArguments().getString(DIFFICULTY) : null;
        if (topic == null || difficulty == null) {
            returnToHomeFragmentOnError("quiz for given topic or difficulty doesn't exist");
            return;
        }
        QuestionDifficulty questionDifficulty;
        switch (difficulty) {
            case "easy": {
                questionDifficulty = QuestionDifficulty.EASY;
                break;
            }
            case "medium": {
                questionDifficulty = QuestionDifficulty.MEDIUM;
                break;
            }
            case "hard": {
                questionDifficulty = QuestionDifficulty.HARD;
                break;
            }
            default: {
                returnToHomeFragmentOnError("no valid difficult was selected");
                return;
            }
        }
        switch (topic) {
            case EQUATION: {
                Expressions expressions = new Expressions();
                questions = new ArrayList<>();
                for (int x = 0; x < MAX_NUMBER_OF_QUESTIONS; x++) {
                    Optional<Question> question = expressions.createQuestion(questionDifficulty);
                    if (question.isPresent()) {
                        questions.add(question.get());
                    } else {
                        returnToHomeFragmentOnError("no questions available for selected topic and difficulty");
                        return;
                    }
                }
                break;
            }
            default: {
                returnToHomeFragmentOnError("no quiz available for selected topic");
                return;
            }
        }
        setCurrentQuestionNumber();
        questionTextView.setText(questions.get(currentQuestion - 1).getQuestion().orElse(""));

    }

    private void setCurrentQuestionNumber() {
        questionNumber.setText(String.format("%d/%d", currentQuestion, MAX_NUMBER_OF_QUESTIONS));
    }

    private void returnToHomeFragmentOnError(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        activity.changeFragment(new HomeFragment(), R.id.nav_home);
    }

    private void onNextButtonClick(View v) {
        if (currentQuestion == MAX_NUMBER_OF_QUESTIONS) {
            if (haveAllQuestionsBeenChecked()) {
                Bundle bundle = new Bundle();
                bundle.putString(TOPIC, topic);
                bundle.putString(DIFFICULTY, difficulty);
                bundle.putInt(SCORE, getNumberOfCorrectAnswers());
                FinishQuizFragment finishedFragment = new FinishQuizFragment();
                finishedFragment.setArguments(bundle);
                activity.changeFragment(finishedFragment);
            } else {
                 Toast.makeText(activity, "Not all answers have been checked", Toast.LENGTH_LONG).show();
            }
            return;
        }
        saveCurrentAnswer();
        if (questionAnswered[currentQuestion]) {
            setViewForAnsweredQuestion(0);
        } else {
            setViewForNextQuestion(0);
        }
        currentQuestion++;
        setCurrentQuestionNumber();
        if (currentQuestion == MAX_NUMBER_OF_QUESTIONS) {
            nextButton.setText(R.string.finish);
        } else {
            previousButton.setVisibility(View.VISIBLE);
        }
        displayNextQuestion();
    }

    private void onPreviousButtonClick(View v) {
        if (currentQuestion == MAX_NUMBER_OF_QUESTIONS) {
            nextButton.setText(R.string.bottom_quiz_next);
        }
        saveCurrentAnswer();
        if (questionAnswered[currentQuestion - 2]) {
            setViewForAnsweredQuestion(2);
        } else {
            setViewForNextQuestion(2);
        }
        currentQuestion--;
        setCurrentQuestionNumber();
        if (currentQuestion == 1) {
            previousButton.setVisibility(View.INVISIBLE);
        }
        displayNextQuestion();
    }

    private void displayNextQuestion() {
        Question question = questions.get(currentQuestion - 1);
        if (question.getQuestion().isPresent()) {
            questionTextView.setText(question.getQuestion().get());
        } else {
            returnToHomeFragmentOnError("No question was found");
        }
    }

    private void setViewForNextQuestion(int x) {
        answerPopup.setVisibility(View.INVISIBLE);
        checkAnswerButton.setVisibility(View.VISIBLE);
        fractionSwitch.setVisibility(View.VISIBLE);
        if (fractionSwitch.isChecked()) {
            answerLeft.setText(multiAnswers[currentQuestion - x][0]);
            answerRight.setText(multiAnswers[currentQuestion - x][1]);
            answerLeft.setVisibility(View.VISIBLE);
            fractionSeparator.setVisibility(View.VISIBLE);
            answerRight.setVisibility(View.VISIBLE);
        } else {
            answerCentre.setText(singleAnswers[currentQuestion - x]);
            answerCentre.setVisibility(View.VISIBLE);
        }
    }

    private void onCheckAnswerButtonClick(View v) {
        if (fractionSwitch.isChecked() && (answerLeft.getText().toString().isEmpty() || answerRight.getText().toString().isEmpty())) {
            Toast.makeText(activity, R.string.answer_needed, Toast.LENGTH_SHORT).show();
        } else if (!fractionSwitch.isChecked() && answerCentre.getText().toString().isEmpty()) {
            Toast.makeText(activity, R.string.answer_needed, Toast.LENGTH_SHORT).show();
        } else {
            questionAnswered[currentQuestion - 1] = true;

            double answer;
            if (fractionSwitch.isChecked()) {
                double answerLeftSide = Double.parseDouble(answerLeft.getText().toString());
                double answerRightSide = Double.parseDouble(answerRight.getText().toString());
                answer = answerLeftSide / answerRightSide;
            } else {
                answer = Double.parseDouble(answerCentre.getText().toString());
            }
            singleAnswers[currentQuestion - 1] = String.valueOf(Question.round(answer));
            setViewForAnsweredQuestion(1);

        }
    }

    private void setViewForAnsweredQuestion(int i) {
        responseTextView.setText(questions.get(currentQuestion - i).isAnswerCorrect(Double.parseDouble(singleAnswers[currentQuestion - i])) ? R.string.correct : R.string.incorrect);

        answerPopup.setVisibility(View.VISIBLE);
        userAnswerTextView.setText(String.format("Your Answer: %s", singleAnswers[currentQuestion - i]));
        correctAnswerTextView.setText(String.format("The correct answer is: %s", questions.get(currentQuestion - i).solveQuestion().orElse(0d)));

        checkAnswerButton.setVisibility(View.INVISIBLE);
        fractionSwitch.setVisibility(View.INVISIBLE);
        answerCentre.setVisibility(View.INVISIBLE);
        answerLeft.setVisibility(View.INVISIBLE);
        fractionSeparator.setVisibility(View.INVISIBLE);
        answerRight.setVisibility(View.INVISIBLE);
    }

    private void onFractionSwitchClick(View v) {
        Switch switchButton = (Switch) v;
        if (switchButton.isChecked()) {
            answerCentre.setVisibility(View.INVISIBLE);
            answerLeft.setVisibility(View.VISIBLE);
            answerLeft.setText(multiAnswers[currentQuestion - 1][0]);
            answerRight.setVisibility(View.VISIBLE);
            answerRight.setText(multiAnswers[currentQuestion - 1][1]);
            fractionSeparator.setVisibility(View.VISIBLE);
        } else {
            answerCentre.setVisibility(View.VISIBLE);
            answerCentre.setText(singleAnswers[currentQuestion - 1]);
            answerLeft.setVisibility(View.INVISIBLE);
            answerRight.setVisibility(View.INVISIBLE);
            fractionSeparator.setVisibility(View.INVISIBLE);
        }
    }

    private void saveCurrentAnswer() {
        if (!questionAnswered[currentQuestion - 1]) {
            if (fractionSwitch.isChecked()) {
                multiAnswers[currentQuestion - 1][0] = answerLeft.getText().toString();
                multiAnswers[currentQuestion - 1][1] = answerRight.getText().toString();
            } else {
                singleAnswers[currentQuestion - 1] = answerCentre.getText().toString();
            }
        }
    }

    private boolean haveAllQuestionsBeenChecked() {
        for (boolean b : questionAnswered) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    private int getNumberOfCorrectAnswers() {
        int correctAnswers = 0;
        for (int x = 0; x < questions.size(); x++) {
            if (questions.get(x).isAnswerCorrect(Double.parseDouble(singleAnswers[x]))) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

}
