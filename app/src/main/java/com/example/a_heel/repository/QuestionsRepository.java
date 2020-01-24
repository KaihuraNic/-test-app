package com.example.a_heel.repository;

import android.app.Application;

import com.example.a_heel.dao.QuestionDao;
import com.example.a_heel.dao.ServeyDao;
import com.example.a_heel.database.AppDatabase;
import com.example.a_heel.models.Question;

import java.util.List;

public class QuestionsRepository {

    private QuestionDao questionDao;
    private ServeyDao serveyDao;
    private static final int completed_survey = 1;

    public QuestionsRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        questionDao = database.getQuestionDao();
        serveyDao = database.geSurveyDao();
    }

    public List<Question> getQuestions(String status) {
        return questionDao.getQuestions(status);
    }

    public void saveQuestion(String finalComplete, Question question, String answer) {
        if (finalComplete.equalsIgnoreCase("finalComplete")) {
            questionDao.saveAnswer(question.getQuestion(), answer);
            serveyDao.updateServey(completed_survey, question.getSurveyId());
        } else {
            questionDao.saveAnswer(question.getQuestion(), answer);
        }
    }

    public List<Question> fetchReciew(String surveyId) {
        return questionDao.fetchPreview(surveyId);
    }
}
