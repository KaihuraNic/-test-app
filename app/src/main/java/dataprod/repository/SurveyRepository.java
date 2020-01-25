package dataprod.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import dataprod.dao.QuestionDao;
import dataprod.dao.ServeyDao;
import dataprod.database.AppDatabase;
import dataprod.models.Question;
import dataprod.models.Survey;
import dataprod.seed.DatabaseSeeder;

import java.util.List;

public class SurveyRepository {

    private ServeyDao serveyDao;
    private QuestionDao questionDao;

    LiveData<List<Survey>> surverysLiveData;

    public SurveyRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        serveyDao = database.geSurveyDao();
        questionDao = database.getQuestionDao();
        surverysLiveData = serveyDao.getTakenSurveys();
    }

    public LiveData<List<Survey>> getTakenSurveys() {
        return surverysLiveData;
    }

    public Survey FetchForSync() {
        return serveyDao.fetchForSyc(0);
    }

    public void seedDb() {
        for (Survey survey: new DatabaseSeeder().getSurveys()){
            serveyDao.create(survey);
        }
        for (Question question:new DatabaseSeeder().getQuestions()){
            questionDao.create(question);
        }
    }

    public Survey getByName(String title) {
        return serveyDao.getByName(title);
    }
}
