package com.example.up804392.keystage3mathstutor.ui.guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.up804392.keystage3mathstutor.MainActivity;
import com.example.up804392.keystage3mathstutor.R;
import com.example.up804392.keystage3mathstutor.db.Database;
import com.example.up804392.keystage3mathstutor.db.entities.Guide;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class GuideAboutFragment extends Fragment {
    private static final String TOPIC =  "TOPIC";
    private static final String HEADING = "What are ";
    private MainActivity activity;
    private Database database;
    private TextView guideTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_about, container, false);

        activity = (MainActivity) getActivity();
        ScrollView scrollView = view.findViewById(R.id.scrollView_guide_about);
        guideTextView = scrollView.findViewById(R.id.textView_guide_about);

        if (database == null) {
            database = activity.getDatabase();
        }
        String topic = getArguments().getString(TOPIC);
        setGuide(topic);

        return view;
    }


    private void setGuide(String topic) {
        new Thread(() -> {
            Guide guide = database.guideDao().getGuide(topic, HEADING + topic);
            guideTextView.setText(guide.body);
            activity.setToolbarTitle(guide.heading);
        }).start();
    }


}