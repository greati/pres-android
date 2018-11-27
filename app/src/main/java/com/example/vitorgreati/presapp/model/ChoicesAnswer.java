package com.example.vitorgreati.presapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ChoicesAnswer {

    @SerializedName("question")
    @Expose
    private ChoicesQuestion question;

    @SerializedName("creation_date")
    @Expose
    private Date creationDate;

    @SerializedName("choices")
    @Expose
    private List<Alternative> choices;

    public ChoicesAnswer(ChoicesQuestion question, Date creationDate, List<Alternative> choices) {
        this.question = question;
        this.creationDate = creationDate;
        this.choices = choices;
    }

    public ChoicesQuestion getQuestion() {
        return question;
    }

    public void setQuestion(ChoicesQuestion question) {
        this.question = question;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Alternative> getChoices() {
        return choices;
    }

    public void setChoices(List<Alternative> choices) {
        this.choices = choices;
    }
}
