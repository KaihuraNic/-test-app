package dataprod.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = " question")
public class Question {


    @PrimaryKey
    @ColumnInfo(name = "Id")
    private int Id;

    @ColumnInfo(name = "answer")
    private String answer;

    @ColumnInfo(name = "surveyId")
    private String surveyId;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "nationalIdOfInterViewee")
    private String nationalIdOfInterViewee;


    public Question(int Id, String answer, String surveyId, String question, String nationalIdOfInterViewee) {
        Id = Id;
        this.answer = answer;
        this.surveyId = surveyId;
        this.question = question;
        this.nationalIdOfInterViewee = nationalIdOfInterViewee;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getNationalIdOfInterViewee() {
        return nationalIdOfInterViewee;
    }

    public void setNationalIdOfInterViewee(String nationalIdOfInterViewee) {
        this.nationalIdOfInterViewee = nationalIdOfInterViewee;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
