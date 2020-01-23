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
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionBaseActivity extends AppCompatActivity {
    private Survey contextSurvey;
    private List<Question> contextQuestions = new ArrayList<>();
    private QuestionsViewModel questionsViewModel;
    private final static  String FINAL_COMPLETE ="COMPLETE";

    @BindView(R.id.form)
    LinearLayout form;
    @BindView(R.id.finish)
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_base);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        contextSurvey = new Gson().fromJson(intent.getStringExtra("content"), Survey.class);
        questionsViewModel = ViewModelProviders.of(QuestionBaseActivity.this).get(QuestionsViewModel.class);
        contextQuestions = questionsViewModel.getContextQuestions(contextSurvey.getId());


        loadQuestion();

    }

    private void loadQuestion() {
        for (final Question question : contextQuestions) {
            LinearLayout holder = new LinearLayout(QuestionBaseActivity.this);
            holder.setBackgroundColor(getResources().getColor(R.color.dark_grey));


            TextView title = new TextView(QuestionBaseActivity.this);
            title.setText(question.getQuestion());
            title.setGravity(Gravity.LEFT);
            title.setTextColor(Color.RED);
            title.setGravity(Gravity.LEFT);
            holder.addView(title);

            final EditText answer = new EditText(QuestionBaseActivity.this);
            answer.setWidth(400);
            answer.setHeight(40);
            answer.setHint("answer");
            answer.setHintTextColor(Color.RED);
            answer.setGravity(Gravity.LEFT);
            holder.addView(answer);


            Button button = new Button(QuestionBaseActivity.this);
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
                    Intent intent = new Intent(QuestionBaseActivity.this, SurveryReviewActivity.class);
                    intent.putExtra("status", new Gson().toJson(contextSurvey));
                    startActivity(intent);

                }
            });
        } else {
            questionsViewModel.saveQuestion("",question, toString);
        }
    }
}
