package com.example.a_heel.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = " question")
public class Question {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int Id;


    @ColumnInfo(name = "answer")
    private String answer;

    @ColumnInfo(name = "surveyId")
    private int surveyId;

    @ColumnInfo(name = "answersOwner")
    private String nationalIdOfInterViewee;


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

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuestion() {
        return null;
    }

    public String getNationalIdOfInterViewee() {
        return nationalIdOfInterViewee;
    }

    public void setNationalIdOfInterViewee(String nationalIdOfInterViewee) {
        this.nationalIdOfInterViewee = nationalIdOfInterViewee;
    }
}
