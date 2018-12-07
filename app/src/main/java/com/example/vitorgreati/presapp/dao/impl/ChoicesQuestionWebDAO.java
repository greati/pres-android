package com.example.vitorgreati.presapp.dao.impl;

import com.example.vitorgreati.presapp.dao.interfaces.ChoicesQuestionDAO;
import com.example.vitorgreati.presapp.dao.retrofit.ChoicesQuestionDAORetrofit;
import com.example.vitorgreati.presapp.dao.retrofit.provider.RetrofitProvider;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.ChoicesAnswer;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.User;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class ChoicesQuestionWebDAO implements ChoicesQuestionDAO {

    private static ChoicesQuestionWebDAO instance;

    private final ChoicesQuestionDAORetrofit choicesQuestionRetrofit;

    private ChoicesQuestionWebDAO(){
        this.choicesQuestionRetrofit = RetrofitProvider.getInstance().getRetrofit().create(ChoicesQuestionDAORetrofit.class);
    }

    public static ChoicesQuestionWebDAO getInstance() {
        if (instance == null)
            instance = new ChoicesQuestionWebDAO();
        return instance;
    }

    @Override
    public ChoicesQuestion create(ChoicesQuestion q) throws WebException {

        try {
            Response<ChoicesQuestion> question = choicesQuestionRetrofit.create(q).execute();

            if (question.code() == 200) {
                return question.body();
            }
            //TODO handle errors
        } catch (IOException e) {
            throw new WebException(e);
        }
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
    public Boolean open(User u, ChoicesQuestion q) throws WebException {

        try {
            Response<ChoicesQuestion> question = choicesQuestionRetrofit.open(u.getId(), q.getId()).execute();

            if (question.code() == 200){
                return true;
            }
            //TODO improve error
            else {
                throw new WebException("Fail to open");
            }
        } catch (IOException e) {
            throw new WebException(e);
        }
    }

    @Override
    public Boolean close(User u, ChoicesQuestion q) throws WebException {
        try {
            Response<ChoicesQuestion> question = choicesQuestionRetrofit.close(u.getId(), q.getId()).execute();

            if (question.code() == 200){
                return true;
            }
            //TODO improve error
            else {
                throw new WebException("Fail to open");
            }
        } catch (IOException e) {
            throw new WebException(e);
        }
    }

    @Override
    public List<ChoicesQuestion> list(PresSession s) throws WebException {

        try {
            Response<List<ChoicesQuestion>> questions = choicesQuestionRetrofit.list(s.getId()).execute();

            if (questions.code() == 200) {
                return questions.body();
            }
            //TODO errors
        } catch (IOException e) {
            throw new WebException(e);
        }
        return null;
    }

    @Override
    public ChoicesQuestion read(ChoicesQuestion q) {
        return null;
    }

    @Override
    public ChoicesAnswer answer(User u, ChoicesAnswer answer) throws WebException {

        try {
            Response<ChoicesAnswer> answerResp = choicesQuestionRetrofit.answer(answer).execute();

            if (answerResp.code() == 200) {
                return answerResp.body();
            }else {
                //TODO better error handling
                throw new WebException(answerResp.message());
            }

        } catch (IOException e) {
            throw new WebException(e);
        }

    }
}
