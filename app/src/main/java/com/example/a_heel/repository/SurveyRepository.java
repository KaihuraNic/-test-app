package com.example.a_heel.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.a_heel.dao.QuestionDao;
import com.example.a_heel.dao.ServeyDao;
import com.example.a_heel.database.AppDatabase;
import com.example.a_heel.models.Question;
import com.example.a_heel.models.Survey;
import com.example.a_heel.seed.DatabaseSeeder;

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
