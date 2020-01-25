package dataprod.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import dataprod.models.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(Question question);

    @Query("select * from ` question` where surveyId =:surveyId")
    List<Question> getQuestions(String surveyId);

    @Query("update ` question` set answer =:answer where question =:tittl")
    void saveAnswer(String tittl , String answer);

    @Query("select * from ` question` where surveyId =:surveyId")
    List<Question> fetchPreview(String surveyId);

}
