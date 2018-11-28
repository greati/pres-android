package com.example.vitorgreati.presapp.dao.impl;

import com.example.vitorgreati.presapp.dao.interfaces.PresSessionDAO;
import com.example.vitorgreati.presapp.dao.retrofit.PresSessionDAORetrofit;
import com.example.vitorgreati.presapp.dao.retrofit.provider.RetrofitProvider;
import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Participation;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class PresSessionWebDAO implements PresSessionDAO {

    private static PresSessionWebDAO instance;

    private final PresSessionDAORetrofit sessionRetrofit;

    private PresSessionWebDAO(){
        this.sessionRetrofit = RetrofitProvider.getInstance().getRetrofit().create(PresSessionDAORetrofit.class);
    }

    public static PresSessionWebDAO getInstance() {
        if (instance == null)
            instance = new PresSessionWebDAO();
        return instance;
    }

    @Override
    public PresSession create(PresSession s) throws WebException {

        try {
            Response<PresSession> resp = sessionRetrofit.create(s).execute();

            if (resp.code() == 200) {
                return resp.body();
            }
            //TODO error handling
        } catch (IOException e) {
            throw new WebException(e);
        }
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
    public PresSession read(PresSession s) {
        return null;
    }

    @Override
    public PresSession getByCode(String code) {
        return null;
    }

    @Override
    public List<PresSession> list(Presentation p) throws WebException {

        try {
            Response<List<PresSession>> sessions = sessionRetrofit.list(p.getId()).execute();

            if (sessions.code() == 200) {
                return sessions.body();
            }
            //TODO handle errors

        } catch (IOException e) {
            throw new WebException(e);
        }

        return null;
    }
}
