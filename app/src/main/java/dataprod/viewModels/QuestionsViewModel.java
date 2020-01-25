package dataprod.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import dataprod.models.Question;
import dataprod.repository.QuestionsRepository;

import java.util.List;

public class QuestionsViewModel extends AndroidViewModel {

    private QuestionsRepository questionsRepositiry;

    public QuestionsViewModel(@NonNull Application application) {
        super(application);
        questionsRepositiry = new QuestionsRepository(application);
    }

    public List<Question> getContextQuestions(String surveyId) {
        return questionsRepositiry.getQuestions(surveyId);
    }

    public List<Question> fetchReview(String surveyId) {
        return questionsRepositiry.fetchReciew(surveyId);
    }


    public void saveQuestion(String finalComplete, Question question, String toString) {
        questionsRepositiry.saveQuestion(finalComplete, question, toString);
    }
}
