package com.example.vitorgreati.presapp.dao.impl;

import com.example.vitorgreati.presapp.dao.interfaces.PresSessionDAO;
import com.example.vitorgreati.presapp.dao.retrofit.PresSessionDAORetrofit;
import com.example.vitorgreati.presapp.dao.retrofit.provider.RetrofitProvider;
import com.example.vitorgreati.presapp.exception.UnauthorizedOperationException;
import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Participation;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.io.IOException;
import java.util.Date;
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
    public void open(PresSession s, User u, Date openingDate) throws WebException, UnauthorizedOperationException {
        try {
            Response<Void> resp = sessionRetrofit.open(s.getId(), u.getId(), openingDate).execute();

            if(resp.code() == 200) {

            } else if (resp.code() == 401) {
                throw new UnauthorizedOperationException("You are not allowed to open this session");
            } else {
                throw new WebException("You cannot open this session");
            }


        } catch (IOException e) {
            throw new WebException(e);
        }
    }

    @Override
    public void close(PresSession s, User u, Date closingDate) throws UnauthorizedOperationException, WebException {

        try {
            Response<Void> resp = sessionRetrofit.close(s.getId(), u.getId(), closingDate).execute();

            if(resp.code() == 200) {

            } else if (resp.code() == 401) {
                throw new UnauthorizedOperationException("You are not allowed to open this session");
            } else {
                throw new WebException("You cannot open this session");
            }


        } catch (IOException e) {
            throw new WebException(e);
        }

    }

    @Override
    public Participation participate(String s, User u) throws UserNotFoundException, WebException {

        try {
            Response<Participation> resp = sessionRetrofit.participate(s, u.getId()).execute();

            if (resp.code() == 200) {
                return resp.body();
            }
            //TODO more error handling
            else {
                throw new WebException("Operation failed");
            }

        } catch (IOException e) {
            throw new WebException(e);
        }
    }

    @Override
    public Participation quit(String s, User u) throws UserNotFoundException, WebException {
        try {
            Response<Void> resp = sessionRetrofit.quit(s, u.getId()).execute();

            if (resp.code() == 200) {
                return null;
            }
            //TODO more error handling
            else {
                throw new WebException("Operation failed");
            }

        } catch (IOException e) {
            throw new WebException(e);
        }
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

    @Override
    public List<Participation> listParticipations(User u) throws WebException {

        try {
            Response<List<Participation>> parts = sessionRetrofit.listParticipations(u.getId()).execute();

            if (parts.code() == 200) {
                return parts.body();
            }
            //TODO handle errors
            else {
                throw new WebException("Fail to retrieve participation");
            }
        } catch (IOException e) {
            throw new WebException(e);
        }
    }
}
