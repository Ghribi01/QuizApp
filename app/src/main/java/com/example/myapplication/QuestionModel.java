package com.example.myapplication;

public class QuestionModel {
    protected String question;
    protected String option1;
    protected String option2;
    protected String option3;
    protected String option4;
    protected String ans;

    public QuestionModel(String question, String option1, String option2, String option3, String option4, String ans) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ans = ans;
    }
}
