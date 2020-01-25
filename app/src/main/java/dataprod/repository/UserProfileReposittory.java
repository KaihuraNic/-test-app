package dataprod.repository;

import android.app.Application;

import dataprod.dao.UserProfileDao;
import dataprod.database.AppDatabase;
import dataprod.models.UserProfile;

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
