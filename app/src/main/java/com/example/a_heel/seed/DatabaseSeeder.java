package com.example.a_heel.seed;

import com.example.a_heel.models.Question;

import java.util.List;

public class DatabaseSeeder {
    private List<Question> questions;

    // create multiple  questions and surveys

    public List<Question> getQuestions() {
        questions.add(new Question());
        return questions;
    }
}
