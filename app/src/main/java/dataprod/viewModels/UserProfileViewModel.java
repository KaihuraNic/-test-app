package dataprod.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import dataprod.models.UserProfile;
import dataprod.repository.UserProfileReposittory;

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
