package com.example.vitorgreati.presapp.dao.retrofit;

import com.example.vitorgreati.presapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserDAORetrofit {

    @POST("users")
    Call<User> create(@Body User u);

    @POST("users/authentication")
    @FormUrlEncoded
    Call<User> authenticate(@Field("email") String email, @Field("password") String password);

}
