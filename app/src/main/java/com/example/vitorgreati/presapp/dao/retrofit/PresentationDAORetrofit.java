package com.example.vitorgreati.presapp.dao.retrofit;

import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PresentationDAORetrofit {

    @POST("presentations")
    Call<Presentation> create(@Body Presentation p);

    @PUT("presentations")
    Call<Presentation> update(@Body Presentation p);

    @DELETE("presentations")
    void delete(@Body Presentation p);

    @GET("presentations")
    Call<Presentation> read(@Body Presentation p);

    @GET("presentations/{userId}")
    Call<List<Presentation>> list(@Path("userId") String user);

}
