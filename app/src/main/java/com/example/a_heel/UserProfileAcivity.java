package com.example.a_heel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a_heel.models.Survey;
import com.example.a_heel.models.UserProfile;
import com.example.a_heel.viewModels.UserProfileViewModel;
import com.google.gson.Gson;

import butterknife.ButterKnife;

public class UserProfileAcivity extends AppCompatActivity {

    private UserProfileViewModel userProfileViewModel;
    private Survey contextSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_acivity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        contextSurvey = new Gson().fromJson(intent.getStringExtra("content"), Survey.class);

        userProfileViewModel = ViewModelProviders.of(UserProfileAcivity.this).get(UserProfileViewModel.class);

    }


    private void saveUserProfile(){
        userProfileViewModel.saveUserProfile(contextSurvey.getId(),new UserProfile());
    }
}
