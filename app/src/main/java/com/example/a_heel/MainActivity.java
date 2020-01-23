package com.example.a_heel;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_heel.adapters.SurveyListAdapter;
import com.example.a_heel.models.Survey;
import com.example.a_heel.viewModels.SurveyViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SurveyViewModel surveyViewModel;
    private SurveyListAdapter surveyListAdapter;
    private List<Survey> surveys;
    private final static int TAKEN_SURVEY = 1;
    private final static String TAKEN_SURVEYS = "Taken Surveys";
    private final static String PENDING_SURVEYS = "Taken Surveys";
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

        surveyViewModel.getTaken().observe(MainActivity.this, new Observer<List<Survey>>() {
            @Override
            public void onChanged(List<Survey> data) {
                for (Survey survey : data) {
                    if (survey.getStatus() != TAKEN_SURVEY) {
                        pending.add(survey);
                    } else {
                        taken.add(survey);
                    }
                }
            }
        });
    }

    private void updateUi(List<Survey> data) {
        surveyListAdapter = new SurveyListAdapter(MainActivity.this, data, new SurveyListAdapter.OnItemClickListener() {
            @Override
            public void onTransactionClick(Survey survey) {
                Intent intent = new Intent(MainActivity.this, QuestionBaseActivity.class);
                intent.putExtra("status", new Gson().toJson(survey));
                startActivity(intent);
            }
        });
        surveyRecyclerview.setAdapter(surveyListAdapter);
        surveyRecyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        surveyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.peinding_bt:
                selectedTitle.setText(PENDING_SURVEYS);
                updateUi(pending);
                break;
            case R.id.taken_bt:
                selectedTitle.setText(TAKEN_SURVEYS);
                updateUi(taken);
                break;
        }
    }
}
