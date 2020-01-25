package dataprod.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dataprod.dao.QuestionDao;
import dataprod.dao.ServeyDao;
import dataprod.dao.UserProfileDao;
import dataprod.models.Question;
import dataprod.models.Survey;
import dataprod.models.UserProfile;


@Database(entities = {Survey.class, Question.class, UserProfile.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String DB_NAME = "d%%%%#";


    public static AppDatabase getDatabase(final Application application) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application.getApplicationContext(), AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract QuestionDao getQuestionDao();

    public abstract ServeyDao geSurveyDao();

    public abstract UserProfileDao getUserProfileDao();
}
