package com.example.a_heel.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.a_heel.models.UserProfile;

@Dao
public interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUserProfile(UserProfile userProfile);

    @Query("select * from USER where surveyId =:id")
    UserProfile getUserProfile(int id);
}
