package com.example.a_heel.repository;

import android.app.Application;

import com.example.a_heel.dao.UserProfileDao;
import com.example.a_heel.database.AppDatabase;
import com.example.a_heel.models.UserProfile;

public class UserProfileReposittory {

    private UserProfileDao userProfileDao;

    public UserProfileReposittory(Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        userProfileDao = appDatabase.getUserProfileDao();
    }

    public void saveUserProfile(int surveyId, UserProfile userProfile) {
        userProfile.setSurveyId(surveyId);
        userProfileDao.saveUserProfile(userProfile);
    }

    public UserProfile getUserProfilee(int id) {
        return userProfileDao.getUserProfile(id);
    }
}
