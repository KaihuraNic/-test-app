package dataprod.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import dataprod.models.Survey;

import java.util.List;

@Dao
public interface ServeyDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(Survey survey);

    @Query("select * from survey")
    LiveData<List<Survey>> getTakenSurveys();

    @Query("update survey set status =:status where id =:surveyId")
    void updateServey(int status, String surveyId);

    @Query("select * from survey where syncStatus =:status limit 1")
    Survey fetchForSyc(int status);

    @Query("select * from survey where title =:title")
    Survey getByName(String title);
}
