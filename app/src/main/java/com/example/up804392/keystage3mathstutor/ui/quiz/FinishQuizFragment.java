package com.example.up804392.keystage3mathstutor.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.up804392.keystage3mathstutor.MainActivity;
import com.example.up804392.keystage3mathstutor.R;
import com.example.up804392.keystage3mathstutor.db.Database;
import com.example.up804392.keystage3mathstutor.db.entities.Score;
import com.example.up804392.keystage3mathstutor.ui.home.HomeFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FinishQuizFragment extends Fragment {

    private static final String TOPIC = "TOPIC";
    private static final String DIFFICULTY = "DIFFICULTY";
    private static final String SCORE = "SCORE";
    private static final String TYPE = "QUIZ";
    private static final String FINISH = "Well done \n You have completed %s\n on %s difficulty";
    private static final String FINAL_SCORE = "Your final score is:\n%d";

    private String topic;
    private String difficulty;
    private int score;
    private Database database;

    private TextView messageTextView;
    private TextView scoreTextView;
    private Button homeButton;
    private Button newQuizButton;
    private MainActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = getViewAndSetVariables(inflater, container);

        activity.setToolbarTitle(topic + " quiz - Review");

        if (database == null) {
            database = activity.getDatabase();
        }

        setTextViewsMessage();

        homeButton.setOnClickListener(v -> {
            activity.changeFragment(new HomeFragment(), R.id.nav_home);
        });

        newQuizButton.setOnClickListener(v -> {
            QuizFragment fragment = new QuizFragment();
            fragment.setArguments(getArguments());
            activity.changeFragment(fragment);
        });


        return view;
    }

    private void setTextViewsMessage() {
        new Thread(() -> {
            Score score = database.scoreboardDao().getQuizScoreForGivenTopicAndDifficulty(topic, difficulty);
            if (score == null || score.score < this.score) {
                messageTextView.setText(R.string.highScore);
                score = new Score(topic, TYPE, difficulty, this.score);
                saveNewHighScore(score);
            } else {
            messageTextView.setText(String.format(FINISH, topic, difficulty));
        }
        scoreTextView.setText(String.format(FINAL_SCORE, this.score));
        }).start();

    }

    private void saveNewHighScore(Score score) {
        new Thread(() -> {
            int numberOfUpdates = database.scoreboardDao().updateScore(score);
            if (numberOfUpdates == 0) {
                database.scoreboardDao().insertScore(score);
            }
        }).start();
    }

    private View getViewAndSetVariables(@NonNull LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_quiz_finished, container, false);

        topic = getArguments().getString(TOPIC);
        difficulty = getArguments().getString(DIFFICULTY);
        score = getArguments().getInt(SCORE);

        activity = (MainActivity) getActivity();

        messageTextView = view.findViewById(R.id.textView_finish);
        scoreTextView = view.findViewById(R.id.textView_score);

        homeButton = view.findViewById(R.id.button_home);
        newQuizButton = view.findViewById(R.id.button_repeat);

        return view;
    }

}
