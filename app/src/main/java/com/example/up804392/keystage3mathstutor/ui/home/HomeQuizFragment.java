package com.example.up804392.keystage3mathstutor.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.up804392.keystage3mathstutor.MainActivity;
import com.example.up804392.keystage3mathstutor.R;
import com.example.up804392.keystage3mathstutor.ui.quiz.QuizFragment;
import com.example.up804392.keystage3mathstutor.ui.scoreboard.Scoreboard;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class HomeQuizFragment extends Fragment {

    private static final String TOPIC = "TOPIC";
    private static final String DIFFICULTY = "DIFFICULTY";
    private static final String TYPE = "TYPE";
    private static final String QUIZ = "QUIZ";
    private MainActivity activity;
    private View fragmentView;
    private ConstraintLayout popupLayout;
    private String topic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_home_quiz, container, false);
        popupLayout = fragmentView.findViewById(R.id.constraint_layout_popup);
        activity = (MainActivity) getActivity();
        findButtonsAndSetOnClickAction();
        activity.setToolbarTitle(R.string.app_name);


        return fragmentView;
    }

    private void findButtonsAndSetOnClickAction() {
        Button expressionButton = fragmentView.findViewById(R.id.button_expressions);
        expressionButton.setOnClickListener(this::onClickOpenPopup);
        Button test1Button = fragmentView.findViewById(R.id.button_algebraic_terms);
        test1Button.setOnClickListener(this::onClickOpenPopup);

        ImageButton closePopupButton = popupLayout.findViewById(R.id.imageButton_close);
        closePopupButton.setOnClickListener(v -> popupLayout.setVisibility(View.INVISIBLE));

        Button easyButton = popupLayout.findViewById(R.id.button_easy);
        easyButton.setOnClickListener(this::startQuiz);
        Button mediumButton = popupLayout.findViewById(R.id.button_medium);
        mediumButton.setOnClickListener(this::startQuiz);
        Button hardButton = popupLayout.findViewById(R.id.button_hard);
        hardButton.setOnClickListener(this::startQuiz);

        Button scoreboardButton = fragmentView.findViewById(R.id.button_scoreboard);
        scoreboardButton.setOnClickListener(v -> {
            Scoreboard scoreboardFragment = new Scoreboard();
            Bundle bundle =  new Bundle();
            bundle.putString(TYPE, QUIZ);
            scoreboardFragment.setArguments(bundle);
            activity.changeFragment(scoreboardFragment);
        });
    }

    private void onClickOpenPopup(View v) {
        Button button = (Button) v;
        topic = button.getText().toString();
        popupLayout.setVisibility(View.VISIBLE);
        popupLayout.bringToFront();
    }

    private void startQuiz(View view) {
        Button button = (Button) view;
        if (activity != null) {
            Bundle bundle = new Bundle();
            bundle.putString(TOPIC, topic);
            bundle.putString(DIFFICULTY, button.getText().toString());
            QuizFragment quizFragment = new QuizFragment();
            quizFragment.setArguments(bundle);
            activity.changeFragment(quizFragment);
        } else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }
}
