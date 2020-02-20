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
    private static final String FINAL_SCORE = "Your final score is:\n%d";
    private static final String NEXT_LEVEL = "Well done \n You got a perfect score\n Use the new quiz button to start a new quiz on the next difficultly";
    private boolean useNextDifficulty;

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
            Bundle bundle = getArguments();
            if (useNextDifficulty) {
                if (difficulty.equals(activity.getString(R.string.easy))) {
                    bundle.putString(DIFFICULTY, activity.getString(R.string.medium));
                } else {
                    bundle.putString(DIFFICULTY, activity.getString(R.string.hard));
                }
            }
            fragment.setArguments(bundle);
            activity.changeFragment(fragment);
        });


        return view;
    }

    private void setTextViewsMessage() {
        new Thread(() -> {
            Score scoreEntity = database.scoreboardDao().getQuizScoreForGivenTopicAndDifficulty(topic, difficulty);
            if (score == 10 && (difficulty.equals(activity.getString(R.string.easy)) || difficulty.equals(activity.getString(R.string.medium)))) {
                messageTextView.setText(NEXT_LEVEL);
                useNextDifficulty = true;
                scoreEntity = new Score(topic, TYPE, difficulty, score);
                saveNewHighScore(scoreEntity);
            } else if (scoreEntity == null || scoreEntity.score < score) {
                messageTextView.setText(R.string.highScore);
                scoreEntity = new Score(topic, TYPE, difficulty, score);
                saveNewHighScore(scoreEntity);
            } else {
                messageTextView.setText(String.format(activity.getString(R.string.finish_message), topic, difficulty));
            }
        scoreTextView.setText(String.format(FINAL_SCORE, score));
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
