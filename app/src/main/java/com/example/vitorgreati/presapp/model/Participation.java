package com.example.vitorgreati.presapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Participation implements Serializable {

    @SerializedName("participation_date")
    @Expose
    private Date participationDate;

    @SerializedName("active")
    private Boolean active;

    @SerializedName("session")
    @Expose
    private PresSession session;

    @SerializedName("user")
    @Expose
    private User user;

    public Participation(Date participationDate, PresSession session, User user) {
        this.participationDate = participationDate;
        this.session = session;
        this.user = user;
    }

    public Date getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(Date participationDate) {
        this.participationDate = participationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public PresSession getSession() {
        return session;
    }

    public void setSession(PresSession session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
