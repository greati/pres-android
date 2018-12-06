package com.example.vitorgreati.presapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PresSession implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;


    @SerializedName("questions")
    @Expose
    private List<ChoicesQuestion> questions;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("active")
    @Expose
    private Boolean active;

    @SerializedName("local")
    @Expose
    private Location location;

    @SerializedName("date_time")
    @Expose
    private Date dateTime;

    @SerializedName("creation_date")
    @Expose
    private Date creationDate;

    @SerializedName("presentation")
    @Expose
    private Presentation presentation;

    public PresSession() {}

    public PresSession(Location location, Date dateTime, Presentation presentation) {
        this.location = location;
        this.dateTime = dateTime;
        this.presentation = presentation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }



    public List<ChoicesQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ChoicesQuestion> questions) {
        this.questions = questions;
    }
}
