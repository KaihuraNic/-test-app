package com.example.a_heel.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.a_heel.dao.QuestionDao;
import com.example.a_heel.dao.ServeyDao;
import com.example.a_heel.dao.UserProfileDao;
import com.example.a_heel.models.Question;
import com.example.a_heel.models.Survey;
import com.example.a_heel.models.UserProfile;


@Database(entities = {Survey.class, Question.class, UserProfile.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String DB_NAME = "d%%%%#";


    public static AppDatabase getDatabase(final Application application) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application.getApplicationContext(), AppDatabase.class, DB_NAME).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract QuestionDao getQuestionDao();

    public abstract ServeyDao geSurveyDao();

    public abstract UserProfileDao getUserProfileDao();
}
