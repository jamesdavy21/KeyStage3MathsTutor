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

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class HomeQuizFragment extends Fragment {

    private static final String TOPIC = "TOPIC";
    private static final String DIFFICULTY = "DIFFICULTY";
    private View fragmentView;
    private ConstraintLayout popup;
    private String topic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_home_quiz, container, false);
        popup = fragmentView.findViewById(R.id.constraint_layout_popup);
        Button OpenPopupButton = fragmentView.findViewById(R.id.button_test);
        OpenPopupButton.setOnClickListener(v -> {
            Button button = (Button) v;
            topic = button.getText().toString();
            popup.setVisibility(View.VISIBLE);
            popup.bringToFront();
        });

        ImageButton closePopupButton = popup.findViewById(R.id.imageButton_close);
        closePopupButton.setOnClickListener(v -> popup.setVisibility(View.INVISIBLE));

        Button easyButton = popup.findViewById(R.id.button_easy);
        easyButton.setOnClickListener(v -> {
            Button button = (Button) v;
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(TOPIC, topic);
            intent.putExtra(DIFFICULTY, button.getText().toString());
            startActivity(intent);
        });

        return fragmentView;
    }
}
