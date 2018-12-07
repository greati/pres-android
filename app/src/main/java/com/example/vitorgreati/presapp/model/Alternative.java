package com.example.vitorgreati.presapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Alternative implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("order")
    @Expose
    private Integer order;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    private transient Boolean checked;

    public Alternative() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Alternative(String text, Integer order) {
        this.text = text;
        this.order = order;
        this.checked = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
