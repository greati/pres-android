package com.example.vitorgreati.presapp.dao.impl;

import com.example.vitorgreati.presapp.dao.interfaces.PresentationDAO;
import com.example.vitorgreati.presapp.dao.retrofit.PresentationDAORetrofit;
import com.example.vitorgreati.presapp.dao.retrofit.provider.RetrofitProvider;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

public class PresentationWebDAO implements PresentationDAO {

    private static PresentationWebDAO instance;

    private final PresentationDAORetrofit presRetrofit;

    private PresentationWebDAO(){
        this.presRetrofit = RetrofitProvider.getInstance().getRetrofit().create(PresentationDAORetrofit.class);
    }

    public static PresentationWebDAO getInstance() {
        if (instance == null)
            instance = new PresentationWebDAO();
        return instance;
    }

    @Override
    public void create(Presentation p) {

    }

    @Override
    public void update(Presentation p) {

    }

    @Override
    public void delete(Presentation p) {

    }

    @Override
    public Presentation read(Presentation p) {
        return null;
    }

    @Override
    public List<Presentation> list(User u) {
        return null;
    }
}
