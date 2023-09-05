package com.example.quiz;

import java.io.Serializable;

public class AnswersData implements Serializable {
    String question,id,explanation,correct_option,yourInput;
    public  AnswersData(String id, String question, String correct_option, String explanation,String yourInput){
        this.id=id;
        this.question=question;
        this.correct_option=correct_option;
        this.explanation=explanation;
        this.yourInput=yourInput;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(String correct_option) {
        this.correct_option = correct_option;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getYourInput() {
        return yourInput;
    }

    public void setYourInput(String yourInput) {
        this.yourInput = yourInput;
    }
}
