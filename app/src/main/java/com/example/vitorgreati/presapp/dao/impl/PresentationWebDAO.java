package com.example.vitorgreati.presapp.dao.impl;

import com.example.vitorgreati.presapp.dao.interfaces.PresentationDAO;
import com.example.vitorgreati.presapp.dao.retrofit.PresentationDAORetrofit;
import com.example.vitorgreati.presapp.dao.retrofit.provider.RetrofitProvider;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

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
    public Presentation create(Presentation p) throws WebException {

        try {
            Response<Presentation> newPres = presRetrofit.create(p).execute();

            if (newPres.code() == 200) {
                return newPres.body();
            }
            //TODO errors

        } catch (IOException e) {
            throw new WebException();
        }

        return null;
    }

    @Override
    public Presentation update(Presentation p) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Presentation p) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Presentation read(Presentation p) throws WebException {

        try {
            Response<Presentation> presResp = presRetrofit.read(p.getId()).execute();

            if (presResp.code() == 200) {
                return presResp.body();
            }
            //TODO errors

        } catch (IOException e) {
            throw new WebException();
        }

        return null;
    }

    @Override
    public List<Presentation> list(User u) throws WebException {

        try {
            Response<List<Presentation>> resp = presRetrofit.list(u.getId()).execute();

            if (resp.code() == 200) {
                return resp.body();
            }
            //TODO erros

        } catch (IOException e) {
            throw new WebException();
        }

        return null;
    }
}
