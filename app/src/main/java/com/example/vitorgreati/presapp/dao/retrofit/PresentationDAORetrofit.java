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

    @PUT("presentations/{presId}")
    Call<Presentation> update(@Path("presId") String presId);

    @DELETE("presentations/{presId}")
    void delete(@Path("presId") String presId);

    @GET("presentations/{presId}")
    Call<Presentation> read(@Path("presId") String presId);

    @GET("users/{userId}/presentations")
    Call<List<Presentation>> list(@Path("userId") String user);

}
