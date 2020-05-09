package com.example.up804392.keystage3mathstutor.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "scoreboard", primaryKeys = {"topic", "type", "difficulty"})
public class Score {

    public Score(String topic, String type, String difficulty, int score) {
        this.topic = topic;
        this.type = type;
        this.difficulty = difficulty;
        this.score = score;
    }

    @NonNull
    @ColumnInfo(name = "topic")
    public String topic;

    @NonNull
    @ColumnInfo(name = "type")
    public String type;

    @NonNull
    @ColumnInfo(name = "difficulty")
    public String difficulty;

    @ColumnInfo(name = "score")
    public int score;
}
