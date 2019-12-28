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

import com.example.up804392.keystage3mathstutor.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {

    private EditText answerCentre;
    private EditText answerLeft;
    private EditText answerRight;
    private TextView fractionSeparator;
    private TextView question;
    private TextView questionNumber;
    private Button nextButton;
    private Button previousButton;
    private Button checkAnswerButton;
    private Switch fractionSwitch;
    private int currentQuestion = 1;
    private static final int MAX_NUMBER_OF_QUESTIONS = 10;

    //list of questions
    private String[] singleAnswers;
    private String[][] multiAnswers;
    private boolean[] questionAnswered;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        singleAnswers = new String[10];
        multiAnswers = new String[10][2];
        questionAnswered = new boolean[10];

        answerCentre = view.findViewById(R.id.editText_answer);
        answerLeft = view.findViewById(R.id.editText_left);
        answerRight = view.findViewById(R.id.editText_right);

        fractionSeparator = view.findViewById(R.id.textView_separator);
        questionNumber = view.findViewById(R.id.textView_question_number);
        question = view.findViewById(R.id.textView_question);

        questionNumber.setText(String.format("%d/%d", currentQuestion, MAX_NUMBER_OF_QUESTIONS));

        nextButton = view.findViewById(R.id.button_next);
        previousButton = view.findViewById(R.id.button_previous);
        checkAnswerButton = view.findViewById(R.id.button_check);

        nextButton.setOnClickListener(v ->  {
            if (currentQuestion == MAX_NUMBER_OF_QUESTIONS) {
                //finish quiz
                return;
            }
            if (questionAnswered[currentQuestion]) {
                checkAnswerButton.setVisibility(View.INVISIBLE);
                fractionSwitch.setVisibility(View.INVISIBLE);
                answerCentre.setVisibility(View.INVISIBLE);
                answerLeft.setVisibility(View.INVISIBLE);
                fractionSeparator.setVisibility(View.INVISIBLE);
                answerRight.setVisibility(View.INVISIBLE);
            } else {
                checkAnswerButton.setVisibility(View.VISIBLE);
                fractionSwitch.setVisibility(View.VISIBLE);
                if (fractionSwitch.isChecked()) {
                    multiAnswers[currentQuestion - 1][0] = answerLeft.getText().toString();
                    multiAnswers[currentQuestion - 1][1] = answerRight.getText().toString();

                    answerLeft.setText(multiAnswers[currentQuestion][0]);
                    answerRight.setText(multiAnswers[currentQuestion][1]);
                    answerLeft.setVisibility(View.VISIBLE);
                    fractionSeparator.setVisibility(View.VISIBLE);
                    answerRight.setVisibility(View.VISIBLE);
                } else {
                    singleAnswers[currentQuestion - 1] = answerCentre.getText().toString();
                    answerCentre.setText(singleAnswers[currentQuestion]);
                    answerCentre.setVisibility(View.VISIBLE);
                }
            }

            currentQuestion++;
            questionNumber.setText(String.format("%d/%d", currentQuestion, MAX_NUMBER_OF_QUESTIONS));
            if (currentQuestion == MAX_NUMBER_OF_QUESTIONS) {
                nextButton.setText(R.string.finish);
            } else {
                previousButton.setVisibility(View.VISIBLE);
            }
        });

        previousButton.setOnClickListener(v ->  {
            if (currentQuestion == MAX_NUMBER_OF_QUESTIONS) {
                nextButton.setText(R.string.bottom_quiz_next);
            }

            if (questionAnswered[currentQuestion - 2]) {
                checkAnswerButton.setVisibility(View.INVISIBLE);
                fractionSwitch.setVisibility(View.INVISIBLE);
                answerCentre.setVisibility(View.INVISIBLE);
                answerLeft.setVisibility(View.INVISIBLE);
                fractionSeparator.setVisibility(View.INVISIBLE);
                answerRight.setVisibility(View.INVISIBLE);
            } else {
                checkAnswerButton.setVisibility(View.VISIBLE);
                fractionSwitch.setVisibility(View.VISIBLE);
                if (fractionSwitch.isChecked()) {
                    multiAnswers[currentQuestion - 1][0] = answerLeft.getText().toString();
                    multiAnswers[currentQuestion - 1][1] = answerRight.getText().toString();

                    answerLeft.setText(multiAnswers[currentQuestion - 2][0]);
                    answerRight.setText(multiAnswers[currentQuestion - 2][1]);

                    answerLeft.setVisibility(View.VISIBLE);
                    fractionSeparator.setVisibility(View.VISIBLE);
                    answerRight.setVisibility(View.VISIBLE);
                } else {
                    singleAnswers[currentQuestion - 1] = answerCentre.getText().toString();
                    answerCentre.setText(singleAnswers[currentQuestion - 2]);

                    answerCentre.setVisibility(View.VISIBLE);
                }
            }

            currentQuestion--;
            questionNumber.setText(String.format("%d/%d", currentQuestion, MAX_NUMBER_OF_QUESTIONS));
            if (currentQuestion == 1) {
                previousButton.setVisibility(View.INVISIBLE);
            }
        });

        checkAnswerButton.setOnClickListener(v -> {
            if (fractionSwitch.isChecked()) {
                if (answerLeft.getText().toString().equals("") || answerRight.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), R.string.answer_needed, Toast.LENGTH_SHORT).show();
                } else {
                    questionAnswered[currentQuestion-1] = true;
                    checkAnswerButton.setVisibility(View.INVISIBLE);
                    answerLeft.setVisibility(View.INVISIBLE);
                    fractionSeparator.setVisibility(View.INVISIBLE);
                    answerRight.setVisibility(View.INVISIBLE);
                    fractionSwitch.setVisibility(View.INVISIBLE);
                }
            } else {
                if (answerCentre.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), R.string.answer_needed, Toast.LENGTH_SHORT).show();
                } else {
                    questionAnswered[currentQuestion-1] = true;
                    checkAnswerButton.setVisibility(View.INVISIBLE);
                    answerCentre.setVisibility(View.INVISIBLE);
                    fractionSwitch.setVisibility(View.INVISIBLE);
                }
            }
        });

        fractionSwitch = view.findViewById(R.id.switch_fraction);
        fractionSwitch.setOnClickListener((v -> {
            Switch switchButton = (Switch) v;
            if (switchButton.isChecked()){
                answerCentre.setVisibility(View.INVISIBLE);
                answerLeft.setVisibility(View.VISIBLE);
                answerRight.setVisibility(View.VISIBLE);
                fractionSeparator.setVisibility(View.VISIBLE);
            } else {
                answerCentre.setVisibility(View.VISIBLE);
                answerLeft.setVisibility(View.INVISIBLE);
                answerRight.setVisibility(View.INVISIBLE);
                fractionSeparator.setVisibility(View.INVISIBLE);
            }
        }));

        return view;
    }

}
