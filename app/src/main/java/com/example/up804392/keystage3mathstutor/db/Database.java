package com.example.up804392.keystage3mathstutor.db;

import com.example.up804392.keystage3mathstutor.db.entities.Score;
import com.example.up804392.keystage3mathstutor.db.entities.ScoreboardDao;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Score.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract ScoreboardDao scoreboardDao();
}
