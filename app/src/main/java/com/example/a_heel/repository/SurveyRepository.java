package com.example.a_heel.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.a_heel.dao.ServeyDao;
import com.example.a_heel.database.AppDatabase;
import com.example.a_heel.models.Survey;

import java.util.List;

public class SurveyRepository {

    private ServeyDao serveyDao;
    LiveData<List<Survey>> surverysLiveData;

    public SurveyRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        serveyDao = database.geSurveyDao();
        surverysLiveData = serveyDao.getTakenSurveys();
    }

    public LiveData<List<Survey>> getTakenSurveys() {
        return surverysLiveData;
    }

    public Survey FetchForSync() {
        return serveyDao.fetchForSyc(0);
    }
}
