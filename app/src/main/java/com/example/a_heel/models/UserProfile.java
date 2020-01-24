package com.example.a_heel.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserProfile {

    @PrimaryKey
    @ColumnInfo(name = "Id")
    private int Id;

    @ColumnInfo(name = "firstname")
    private String firstName;

    @ColumnInfo(name = "lastname")
    private String lastName;

    @ColumnInfo(name = "nationalIdNo")
    private String nationalIdNo;

    @ColumnInfo(name = "photo")
    private String photo;


    @ColumnInfo(name = "surveyId")
    private int surveyId;


    public UserProfile(int Id, String firstName, String lastName, String nationalIdNo, String photo, int surveyId) {
        Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdNo = nationalIdNo;
        this.photo = photo;
        this.surveyId = surveyId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalIdNo() {
        return nationalIdNo;
    }

    public void setNationalIdNo(String nationalIdNo) {
        this.nationalIdNo = nationalIdNo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }
}
