package com.example.vitorgreati.presapp.dao.impl;

import com.example.vitorgreati.presapp.dao.interfaces.PresSessionDAO;
import com.example.vitorgreati.presapp.dao.retrofit.PresSessionDAORetrofit;
import com.example.vitorgreati.presapp.dao.retrofit.provider.RetrofitProvider;
import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.model.Participation;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

public class PresSessionWebDAO implements PresSessionDAO {

    private static PresSessionWebDAO instance;

    private final PresSessionDAORetrofit presRetrofit;

    private PresSessionWebDAO(){
        this.presRetrofit = RetrofitProvider.getInstance().getRetrofit().create(PresSessionDAORetrofit.class);
    }

    public static PresSessionWebDAO getInstance() {
        if (instance == null)
            instance = new PresSessionWebDAO();
        return instance;
    }

    @Override
    public PresSession create(PresSession s) {
        return null;
    }

    @Override
    public PresSession update(PresSession s) {
        return null;
    }

    @Override
    public void delete(PresSession s) {

    }

    @Override
    public void open(PresSession s) {

    }

    @Override
    public void close(PresSession s) {

    }

    @Override
    public Participation participate(PresSession s, User u) throws UserNotFoundException {
        return null;
    }

    @Override
    public Participation quit(PresSession s, User u) throws UserNotFoundException {
        return null;
    }

    @Override
    public PresSession get(String code) {
        return null;
    }

    @Override
    public List<PresSession> list(Presentation p) {
        return null;
    }
}
