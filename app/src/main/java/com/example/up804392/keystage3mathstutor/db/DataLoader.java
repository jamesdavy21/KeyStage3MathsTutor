package com.example.up804392.keystage3mathstutor.db;

import com.example.up804392.keystage3mathstutor.MainActivity;
import com.example.up804392.keystage3mathstutor.R;
import com.example.up804392.keystage3mathstutor.db.entities.Guide;

public class DataLoader {

    private Database database;
    private MainActivity activity;

    public DataLoader(Database database, MainActivity activity) {
        this.database = database;
        this.activity = activity;
    }


    public void loadDatabase() {
        if (isDataNeededToBeCreated()) {
            addGuide(activity.getString(R.string.Equations), activity.getString(R.string.what_are_equations));
            addGuide(activity.getString(R.string.Equations), activity.getString(R.string.equation_questions), activity.getString(R.string.equation_answers));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isDataNeededToBeCreated() {
        int numberOfGuides = database.guideDao().getNumberOfGuides();

        return numberOfGuides == 0;
    }

    private void addGuide(String topic, String body) {
        database.guideDao().insertGuide(new Guide(topic, "What are " + topic, body, null , null));
    }

    private void addGuide(String topic, String questions, String answers) {
        database.guideDao().insertGuide(new Guide(topic, String.format("Example %s questions", topic), null, questions , answers));
    }



}
