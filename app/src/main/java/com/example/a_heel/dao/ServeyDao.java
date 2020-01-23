package com.example.a_heel.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.a_heel.models.Survey;

import java.util.List;

@Dao
public interface ServeyDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(Survey survey);

    @Query("select * from survey")
    LiveData<List<Survey>> getTakenSurveys();

    @Query("update survey set status =:status where id =:surveyId")
    void updateServey(int status, int surveyId);

    @Query("select * from survey where syctatus =:status limit 1")
    Survey fetchForSyc(int status);
}
