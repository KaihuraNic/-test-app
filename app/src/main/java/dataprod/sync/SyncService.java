package dataprod.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import dataprod.http.HttpUtil;
import dataprod.http.WebInterface;
import dataprod.http.request.SyncData;
import dataprod.models.Question;
import dataprod.models.Survey;
import dataprod.models.UserProfile;
import dataprod.repository.QuestionsRepository;
import dataprod.repository.SurveyRepository;
import dataprod.repository.UserProfileReposittory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncService extends Service {
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //check internet,send data and update databaseFlags
        // run a schedule
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (new HttpUtil().checkConnectivity(getApplicationContext())) {
                    //fetch data and send

                    Survey survey = new SurveyRepository(getApplication()).FetchForSync();
                    List<Question>questions = new QuestionsRepository(getApplication()).fetchReciew(survey.getTitle());
                    UserProfile userProfile = new UserProfileReposittory(getApplication()).getUserProfilee(survey.getId());

                    WebInterface webInterface = new HttpUtil().getRetrofit().create(WebInterface.class);
                    Call<String> call = webInterface.syncData(new SyncData(survey, userProfile, questions));
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            //handle request update local records

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            //handle
                            //handle request update local records
                        }
                    });
                } else {
                    //update db and observe
                }
            }
        }, 0, 30, TimeUnit.SECONDS);
        return Service.START_STICKY;
    }
}
