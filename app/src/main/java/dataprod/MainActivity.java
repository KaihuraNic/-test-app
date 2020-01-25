package dataprod;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dataprod.adapters.SurveyListAdapter;
import dataprod.models.Survey;
import dataprod.sync.SyncService;
import dataprod.viewModels.SurveyViewModel;

import com.dataprod.a_heel.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {

    private SurveyViewModel surveyViewModel;
    private SurveyListAdapter surveyListAdapter;
    private List<Survey> surveys;
    private final static int TAKEN_SURVEY = 1;
    private final static String TAKEN_SURVEYS = "Taken Surveys";
    private final static String PENDING_SURVEYS = "Pending Surveys";
    List<Survey> pending = new ArrayList<>();
    List<Survey> taken = new ArrayList<>();

    @BindView(R.id.surveys_recyclerview)
    RecyclerView surveyRecyclerview;
    @BindView(R.id.selected_title)
    TextView selectedTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        surveyViewModel = ViewModelProviders.of(MainActivity.this).get(SurveyViewModel.class);

        surveyViewModel.seedDb();

        surveyViewModel.getTaken().observe(MainActivity.this, new Observer<List<Survey>>() {
            @Override
            public void onChanged(List<Survey> data) {
                for (Survey survey : data) {
                    if (survey.getStatus() == TAKEN_SURVEY) {
                        taken.add(survey);
                    } else {
                        pending.add(survey);
                    }
                }
            }
        });

        findViewById(R.id.peinding_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTitle.setText(PENDING_SURVEYS);
                updateUi(pending);
            }
        });

        findViewById( R.id.taken_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTitle.setText(TAKEN_SURVEYS);
                updateUi(taken);
            }
        });

        Intent intent =  new Intent(MainActivity.this, SyncService.class);
        startService(intent);
    }

    private void updateUi(List<Survey> data) {
        surveyListAdapter = new SurveyListAdapter(MainActivity.this, data, new SurveyListAdapter.OnItemClickListener() {
            @Override
            public void onTransactionClick(Survey survey) {
                if (survey.getId() != TAKEN_SURVEY) {
                    Intent intent = new Intent(MainActivity.this, UserProfileAcivity.class);
                    surveyViewModel.getSurveyByName(survey.getTitle());
                    intent.putExtra("content", new Gson().toJson(survey));
                    startActivity(intent);
                }
            }
        });
        surveyRecyclerview.setAdapter(surveyListAdapter);
        surveyRecyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        surveyListAdapter.notifyDataSetChanged();
    }

}
