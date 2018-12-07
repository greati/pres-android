package com.example.vitorgreati.presapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChoicesQuestion implements Serializable {


    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("alternatives")
    @Expose
    private List<Alternative> alternatives;

    @SerializedName("session")
    @Expose
    private PresSession session;

    @SerializedName("creation_date")
    @Expose
    private Date creationDate;

    public ChoicesQuestion() {}

    public ChoicesQuestion(String title, List<Alternative> alternatives) {
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

    public PresSession getSession() {
        return session;
    }

    public void setSession(PresSession session) {
        this.session = session;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
