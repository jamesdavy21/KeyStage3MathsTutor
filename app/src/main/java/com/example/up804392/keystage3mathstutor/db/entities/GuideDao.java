package com.example.up804392.keystage3mathstutor.db.entities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface GuideDao {

    @Query("SELECT * FROM guides WHERE topic = :topic AND heading = :heading")
    Guide getGuide(String topic, String heading);

    @Query("SELECT COUNT(*) FROM guides")
    int getNumberOfGuides();

    @Transaction
    @Insert
    void insertGuide(Guide guide);
}
