package com.example.vitorgreati.presapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Alternative implements Serializable {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("order")
    @Expose
    private Integer order;

    public Alternative() {}

    public Alternative(String text, Integer order) {
        this.text = text;
        this.order = order;
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
