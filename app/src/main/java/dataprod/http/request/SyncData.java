package dataprod.http.request;

import dataprod.models.Question;
import dataprod.models.Survey;
import dataprod.models.UserProfile;

import java.util.List;

public class SyncData {
    private Survey survey;
    private UserProfile profile;
    private List<Question> questions;

    public SyncData(Survey survey, UserProfile profile, List<Question> questions) {
        this.survey = survey;
        this.profile = profile;
        this.questions = questions;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
