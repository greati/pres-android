package com.example.vitorgreati.presapp.dao.impl;

import com.example.vitorgreati.presapp.dao.interfaces.ChoicesQuestionDAO;
import com.example.vitorgreati.presapp.dao.retrofit.ChoicesQuestionDAORetrofit;
import com.example.vitorgreati.presapp.dao.retrofit.provider.RetrofitProvider;
import com.example.vitorgreati.presapp.model.ChoicesAnswer;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

public class ChoicesQuestionWebDAO implements ChoicesQuestionDAO {

    private static ChoicesQuestionWebDAO instance;

    private final ChoicesQuestionDAORetrofit presRetrofit;

    private ChoicesQuestionWebDAO(){
        this.presRetrofit = RetrofitProvider.getInstance().getRetrofit().create(ChoicesQuestionDAORetrofit.class);
    }

    public static ChoicesQuestionWebDAO getInstance() {
        if (instance == null)
            instance = new ChoicesQuestionWebDAO();
        return instance;
    }

    @Override
    public ChoicesQuestion create(ChoicesQuestion q) {
        return null;
    }

    @Override
    public ChoicesQuestion update(ChoicesQuestion q) {
        return null;
    }

    @Override
    public void delete(ChoicesQuestion q) {

    }

    @Override
    public void open(User u, ChoicesQuestion q) {

    }

    @Override
    public void close(User u, ChoicesQuestion q) {

    }

    @Override
    public List<ChoicesQuestion> list(PresSession s) {
        return null;
    }

    @Override
    public ChoicesQuestion get(ChoicesQuestion q) {
        return null;
    }

    @Override
    public ChoicesAnswer answer(User u, ChoicesAnswer answer) {
        return null;
    }
}
