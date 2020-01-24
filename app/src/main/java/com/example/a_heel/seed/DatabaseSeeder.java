package com.example.a_heel.seed;

import com.example.a_heel.models.Question;
import com.example.a_heel.models.Survey;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSeeder {
    private List<Question> questions = new ArrayList<>();
    private List<Survey> surveys  =  new ArrayList<>();


    public List<Question> getQuestions() {
        questions.add(new Question(1,"","Basic","what is your favourite colour?",""));
        questions.add(new Question(1,"","Basic","what is your favourite animal?",""));
        questions.add(new Question(1,"","Basic","what is your favourite town?",""));
        questions.add(new Question(2,"","Basic","what is your favourite personality?",""));
        questions.add(new Question(2,"","Domestic","what is your favourite Food?",""));
        questions.add(new Question(2,"","Domestic","what is your favourite Drink?",""));
        questions.add(new Question(3,"","Financial","what is your favourite Mountain?",""));
        questions.add(new Question(3,"","Financial","what is your favourite Ocean?",""));
        questions.add(new Question(4,"","Gender","what is your favourite River?",""));
        questions.add(new Question(4,"","Gender","what is your favourite Car?",""));
        questions.add(new Question(5,"","Gender","what is your favourite Team?",""));
        questions.add(new Question(5,"","Gender","what is your favourite Season?",""));
        return questions;
    }

    public List<Survey> getSurveys(){
        surveys.add(new Survey(1,"Basic",0,0));
        surveys.add(new Survey(2,"Domestic",0,0));
        surveys.add(new Survey(3,"Financial",0,0));
        surveys.add(new Survey(4,"Education",0,0));
        surveys.add(new Survey(5,"Gender",0,0));

        return surveys;
    }
}
