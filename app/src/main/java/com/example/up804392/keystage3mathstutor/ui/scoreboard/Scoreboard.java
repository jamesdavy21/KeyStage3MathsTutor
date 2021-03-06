package com.example.up804392.keystage3mathstutor.ui.scoreboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.up804392.keystage3mathstutor.MainActivity;
import com.example.up804392.keystage3mathstutor.R;
import com.example.up804392.keystage3mathstutor.db.Database;
import com.example.up804392.keystage3mathstutor.db.entities.Score;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

public class Scoreboard extends Fragment {
    private static final String TYPE = "TYPE";
    private static final String QUIZ = "QUIZ";
    private static final String GAME = "GAME";

    private View fragmentView;
    private ScrollView scrollView;
    private TableLayout table;
    private MainActivity activity;
    private Database database;
    private MutableLiveData<List<TableRow>> tableRowListener = new MutableLiveData<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_scoreboard, container, false);
        activity = (MainActivity) getActivity();

        scrollView = fragmentView.findViewById(R.id.scrollView_tables);
        table = scrollView.findViewById(R.id.tableLayout_scoreboard);
        activity.uncheckCurrentCheckedItemInNavigationView();
        database = activity.getDatabase();

        BottomNavigationView bottomNavigationView = fragmentView.findViewById(R.id.nav_bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        if (getArguments() != null) {
            if (getArguments().getString(TYPE).equals(GAME)) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_game);
            } else {
                bottomNavigationView.setSelectedItemId(R.id.navigation_quiz);
            }
        } else {
            bottomNavigationView.setSelectedItemId(R.id.navigation_quiz);
        }

        tableRowListener.observe(this, tableRows -> {
            for (TableRow tableRow: tableRows) {
                table.addView(tableRow);
            }
        });



        return fragmentView;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = menuItem -> {
        if (menuItem.getItemId() == R.id.navigation_game) {
            setTable(GAME);
        } else {
            setTable(QUIZ);
        }

        return true;
    };

    private void resetTable() {
        table.removeAllViews();

        TableRow tableRow = new TableRow(getContext());

        tableRow.addView(createTableTextView("topic"));
        tableRow.addView(createTableTextView("difficulty"));
        tableRow.addView(createTableTextView("score"));

        table.addView(tableRow);
    }

    private void setTable(String type) {
        resetTable();
        new Thread(() -> {
            List<Score> scores = database.scoreboardDao().getAllQuizScores(type);
            List<TableRow> tableRows = new ArrayList<>();
            for (Score score : scores) {
                TableRow tableRow = new TableRow(getContext());

                tableRow.addView(createTableTextView(score.topic));

                tableRow.addView(createTableTextView(score.difficulty));

                tableRow.addView(createTableTextView(String.valueOf(score.score)));

                tableRows.add(tableRow);
            }
            tableRowListener.postValue(tableRows);

        }).start();
    }

    private TextView createTableTextView(String value) {
        TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(0, 10, 0, 10);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlack));
        return textView;
    }

}
