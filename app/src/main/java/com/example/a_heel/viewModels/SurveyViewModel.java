package com.example.a_heel.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.a_heel.models.Survey;
import com.example.a_heel.repository.SurveyRepository;

import java.util.List;

public class SurveyViewModel extends AndroidViewModel {

    private SurveyRepository surveyRepository;

    public SurveyViewModel(@NonNull Application application) {
        super(application);
        surveyRepository =  new SurveyRepository(application);
    }

    public LiveData<List<Survey>> getTaken() {
        return surveyRepository.getTakenSurveys();
    }

    public void seedDb() {
        surveyRepository.seedDb();
    }

    public void getSurveyByName(String title) {
        surveyRepository.getByName(title);
    }
}
