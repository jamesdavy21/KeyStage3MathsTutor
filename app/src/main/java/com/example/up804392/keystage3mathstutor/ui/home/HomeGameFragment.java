package com.example.up804392.keystage3mathstutor.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.up804392.keystage3mathstutor.MainActivity;
import com.example.up804392.keystage3mathstutor.R;
import com.example.up804392.keystage3mathstutor.ui.scoreboard.Scoreboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HomeGameFragment extends Fragment {

    private static final String TYPE = "TYPE";
    private static final String GAME = "GAME";

    private MainActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_game, container, false);

        activity = (MainActivity) getActivity();

        Button scoreboardButton = view.findViewById(R.id.button_scoreboard);
        scoreboardButton.setOnClickListener(v -> {
            Scoreboard scoreboardFragment = new Scoreboard();
            Bundle bundle =  new Bundle();
            bundle.putString(TYPE, GAME);
            scoreboardFragment.setArguments(bundle);
            activity.changeFragment(scoreboardFragment);
        });

        return view;
    }
}
