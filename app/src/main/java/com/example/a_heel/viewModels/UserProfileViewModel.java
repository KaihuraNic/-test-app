package com.example.a_heel.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.a_heel.models.UserProfile;
import com.example.a_heel.repository.UserProfileReposittory;

public class UserProfileViewModel extends AndroidViewModel {
    private UserProfileReposittory userProfileReposittory;
    public UserProfileViewModel(@NonNull Application application) {
        super(application);
        userProfileReposittory =  new UserProfileReposittory(application);
    }
    public  void saveUserProfile(int surveyId,UserProfile userProfile){
        userProfileReposittory.saveUserProfile(surveyId,userProfile);
    }
}
