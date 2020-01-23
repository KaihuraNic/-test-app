package com.example.a_heel.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.a_heel.models.Question;
import com.example.a_heel.models.Survey;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(Question question);

    @Query("select * from ` question` where surveyId =:surveyId")
    List<Question> getQuestions(int surveyId);

    @Query("update ` question` set answer =:answer where Id =:id")
    void saveAnswer(int id, String answer);

    @Query("select * from ` question` where surveyId =:surveyId")
    List<Question> fetchPreview(int surveyId);
}