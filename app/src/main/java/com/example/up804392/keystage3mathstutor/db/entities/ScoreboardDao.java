package com.example.up804392.keystage3mathstutor.db.entities;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ScoreboardDao {

    @Query("SELECT * FROM scoreboard WHERE type = :type ORDER BY score DESC, topic")
    List<Score> getAllQuizScores(String type);

    @Query("SELECT * FROM scoreboard WHERE topic = :topic AND type = 'QUIZ' and difficulty = :difficulty")
    Score getQuizScoreForGivenTopicAndDifficulty(String topic, String difficulty);

    @Insert
    void insertScore(Score scoreboard);

    @Update
    int updateScore(Score scoreboard);
}
