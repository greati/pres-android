package com.example.vitorgreati.presapp.exception;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super("Fail to authenticate user");
    }

    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException(Exception e) {
        super(e);
    }

}
