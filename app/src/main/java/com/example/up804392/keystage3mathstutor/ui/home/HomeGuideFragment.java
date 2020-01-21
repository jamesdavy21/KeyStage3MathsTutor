package com.example.up804392.keystage3mathstutor.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.up804392.keystage3mathstutor.MainActivity;
import com.example.up804392.keystage3mathstutor.R;
import com.example.up804392.keystage3mathstutor.ui.guide.GuideFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HomeGuideFragment extends Fragment {

    private static final String TOPIC = "TOPIC";
    private MainActivity activity;
    private View fragmentView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_home_guide, container, false);
        activity = (MainActivity) getActivity();
        findButtonsAndSetOnClickAction();

        return fragmentView;
    }

    private void findButtonsAndSetOnClickAction() {
        Button expressionButton = fragmentView.findViewById(R.id.button_expressions);
        expressionButton.setOnClickListener(this::openGuide);
    }

    private void openGuide(View v) {
        Button button = (Button) v;
        String topic = button.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(TOPIC, topic);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(bundle);
        activity.changeFragment(fragment);
        activity.uncheckCurrentCheckedItemInNavigationView();
    }
}
