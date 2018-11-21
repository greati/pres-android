package com.example.vitorgreati.presapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Question implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("alternatives")
    @Expose
    private List<Alternative> alternatives;

    @SerializedName("creation_date")
    @Expose
    private Date creationDate;

    public Question() {}

    public Question(String title, List<Alternative> alternatives) {
        this.title = title;
        this.alternatives = alternatives;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }
}
