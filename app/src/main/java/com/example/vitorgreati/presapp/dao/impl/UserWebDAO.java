package com.example.vitorgreati.presapp.dao.impl;

import com.example.vitorgreati.presapp.dao.interfaces.UserDAO;
import com.example.vitorgreati.presapp.dao.retrofit.UserDAORetrofit;
import com.example.vitorgreati.presapp.exception.AuthenticationException;
import com.example.vitorgreati.presapp.exception.DuplicateUsernameException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.User;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserWebDAO implements UserDAO {

    private UserDAORetrofit userRetrofit;

    private static UserWebDAO instance;

    public static UserWebDAO getInstance() {
        if (instance == null)
            instance = new UserWebDAO();
        return instance;
    }

    private UserWebDAO() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userRetrofit = retrofit.create(UserDAORetrofit.class);
    }

    @Override
    public User create(User u) throws WebException, DuplicateUsernameException {
        try {
            Response<User> response = userRetrofit.create(u).execute();

            if (response.code() == 200) {
                return response.body();
            } else if (response.code() == 500) {
                throw new DuplicateUsernameException();
            } else {
                throw new WebException();
            }
        } catch (IOException e) {
            throw new WebException(e);
        }
    }

    @Override
    public User authenticate(String email, String password) throws AuthenticationException, WebException {
        try {
            Response<User> response = userRetrofit.authenticate(email, password).execute();

            if (response.code() == 200) {
                return response.body();
            } else if (response.code() == 401 || response.code() == 404) {
                throw new AuthenticationException();
            } else {
                throw new WebException();
            }
        } catch (IOException e) {
            throw new WebException(e);
        }
    }
}
