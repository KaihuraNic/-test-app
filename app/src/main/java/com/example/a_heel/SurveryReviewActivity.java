package com.example.a_heel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a_heel.models.Question;
import com.example.a_heel.models.Survey;
import com.example.a_heel.viewModels.QuestionsViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveryReviewActivity extends AppCompatActivity {

    private QuestionsViewModel questionsViewModel;
    private List<Question> contextQuestions =  new ArrayList<>();
    private Survey contextSurvey;

    private final static  String FINAL_COMPLETE ="COMPLETE";

    @BindView(R.id.form)
    LinearLayout form;
    @BindView(R.id.finish)
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survery_review);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        contextSurvey = new Gson().fromJson(intent.getStringExtra("content"), Survey.class);

        questionsViewModel = ViewModelProviders.of(SurveryReviewActivity.this).get(QuestionsViewModel.class);
        contextQuestions =  questionsViewModel.fetchReview(contextSurvey.getId());
    }

    private void loadQuestion() {
        for (final Question question : contextQuestions) {
            LinearLayout holder = new LinearLayout(SurveryReviewActivity.this);
            holder.setBackgroundColor(getResources().getColor(R.color.dark_grey));


            TextView title = new TextView(SurveryReviewActivity.this);
            title.setText(question.getQuestion());
            title.setGravity(Gravity.LEFT);
            title.setTextColor(Color.RED);
            title.setGravity(Gravity.LEFT);
            holder.addView(title);

            final EditText answer = new EditText(SurveryReviewActivity.this);
            answer.setWidth(400);
            answer.setHeight(40);
            answer.setText(question.getSurveyId());
            answer.setHintTextColor(Color.RED);
            answer.setGravity(Gravity.LEFT);
            holder.addView(answer);


            Button button = new Button(SurveryReviewActivity.this);
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            button.setText("Save");
            button.setWidth(400);
            button.setHeight(40);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(answer.getText().toString())) {
                        saveChanges(question, answer.getText().toString());
                    }
                }
            });

            holder.addView(button);


            form.addView(holder);

        }
    }


    private void saveChanges(Question question, String toString) {
        if (question.getId() == contextQuestions.size() - 1) {

            questionsViewModel.saveQuestion(FINAL_COMPLETE,question, toString);

            finish.setVisibility(View.VISIBLE);
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(SurveryReviewActivity.this, MainActivity.class));
                }
            });
        } else {
            questionsViewModel.saveQuestion("",question, toString);
        }
    }
}
