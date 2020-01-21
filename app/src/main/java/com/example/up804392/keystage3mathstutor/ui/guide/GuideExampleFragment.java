package com.example.up804392.keystage3mathstutor.ui.guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.up804392.keystage3mathstutor.MainActivity;
import com.example.up804392.keystage3mathstutor.R;
import com.example.up804392.keystage3mathstutor.db.Database;
import com.example.up804392.keystage3mathstutor.db.entities.Guide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class GuideExampleFragment extends Fragment {
    private static final String TOPIC = "TOPIC";
    private static final String HEADING = "Example %s questions";
    private MainActivity activity;
    private Database database;
    private List<TextView> questions = new ArrayList<>();
    private List<TextView> answers = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_example, container, false);
        activity = (MainActivity) getActivity();

        getTextViews(view);

        getAndSetButtonViews(view);

        if (database == null) {
            database = activity.getDatabase();
        }

        String topic = getArguments().getString(TOPIC);
        setGuide(topic);

        return view;
    }

    private void getTextViews(View view) {
        questions.add(view.findViewById(R.id.textView_guide_q1));
        questions.add(view.findViewById(R.id.textView_guide_q2));
        questions.add(view.findViewById(R.id.textView_guide_q3));

        answers.add(view.findViewById(R.id.textView_guide_a1));
        answers.add(view.findViewById(R.id.textView_guide_a2));
        answers.add(view.findViewById(R.id.textView_guide_a3));
    }

    private void getAndSetButtonViews(View view) {
        Button revealAnswerButton1 = view.findViewById(R.id.button_reveal1);
        revealAnswerButton1.setOnClickListener(this::onClickAction);

        Button revealAnswerButton2 = view.findViewById(R.id.button_reveal2);
        revealAnswerButton2.setOnClickListener(this::onClickAction);

        Button revealAnswerButton3 = view.findViewById(R.id.button_reveal3);
        revealAnswerButton3.setOnClickListener(this::onClickAction);
    }


    private void setGuide(String topic) {
        new Thread(() -> {
            Guide guide = database.guideDao().getGuide(topic, String.format(HEADING, topic));
            String[] questions = guide.questions.split(",");
            this.questions.get(0).setText(questions[0]);
            this.questions.get(1).setText(questions[1]);
            this.questions.get(2).setText(questions[2]);

            String[] answers = guide.answers.split(",");
            this.answers.get(0).setText(answers[0]);
            this.answers.get(1).setText(answers[1]);
            this.answers.get(2).setText(answers[2]);

            activity.setToolbarTitle(guide.heading);
        }).start();
    }

    private void onClickAction(View v) {
        Button button = (Button) v;
        int key = Integer.valueOf(button.getHint().toString());
        answers.get(key).setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
    }


}