package com.example.up804392.keystage3mathstutor.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "guides", primaryKeys = {"topic", "heading"})
public class Guide {

    public Guide(@NonNull String topic, @NonNull String heading, String body, String questions, String answers) {
        this.topic = topic;
        this.heading = heading;
        this.body = body;
        this.questions = questions;
        this.answers = answers;
    }

    @NonNull
    @ColumnInfo(name = "topic")
    public String topic;

    @NonNull
    @ColumnInfo(name = "heading")
    public String heading;

    @ColumnInfo(name = "body")
    public String body;

    @ColumnInfo(name = "questions")
    public String questions;

    @ColumnInfo(name = "answer")
    public String answers;



}
