package com.example.vitorgreati.presapp.exception;

public class WebException extends Exception {

    public WebException() {
        super("Error on access web api");
    }

    public WebException(String msg) {
        super(msg);
    }

    public WebException(Exception e) {
        super(e);
    }
}
