package com.example.vitorgreati.presapp.dao.retrofit;

import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PresSessionDAORetrofit {

    @POST("sessions")
    void create(@Body PresSession s);

    @PUT("sessions")
    void update(@Body PresSession s);

    @DELETE("sessions")
    void delete(@Body PresSession s);

    @POST("sessions/{sessionId}/open")
    @FormUrlEncoded
    void open(@Path("sessionId") String sessionId, @Field("userId") String userId);

    @POST("sessions/{sessionId}/close")
    void close(@Path("sessionId") String sessionId, @Field("userId") String userId);

    @POST("sessions/{sessionId}/participations/enter")
    @FormUrlEncoded
    void participate(@Path("sessionId") String sessionId, @Field("userId") String userId) throws UserNotFoundException;

    @POST("sessions/{sessionId}/participations/quit")
    @FormUrlEncoded
    void quit(@Path("sessionId") String sessionId, @Field("userId") String userId) throws UserNotFoundException;

    @GET("sessions")
    Call<PresSession> get(@Body PresSession s);

    @GET("presentations/{presId}/sessions")
    Call<List<PresSession>> list(@Path("presId") String presId);

}
