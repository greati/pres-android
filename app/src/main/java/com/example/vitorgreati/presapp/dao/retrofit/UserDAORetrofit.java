package com.example.vitorgreati.presapp.dao.retrofit;

import com.example.vitorgreati.presapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserDAORetrofit {

    @POST("users")
    Call<User> create(@Body User u);

    @POST("users/authentication")
    Call<User> authenticate(@Body String email, @Body String password);

}
