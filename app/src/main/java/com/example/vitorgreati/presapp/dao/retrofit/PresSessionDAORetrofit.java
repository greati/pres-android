package com.example.vitorgreati.presapp.dao.retrofit;

import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.model.Participation;
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
    Call<PresSession> create(@Body PresSession s);

    @PUT("sessions")
    Call<PresSession> update(@Body PresSession s);

    @DELETE("sessions")
    void delete(@Body PresSession s);

    @GET("sessions/{sessionId}")
    Call<PresSession> get(@Path("sessionId") String sessionId);

    @POST("sessions/{sessionId}/open")
    @FormUrlEncoded
    void open(@Path("sessionId") String sessionId, @Field("userId") String userId);

    @POST("sessions/{sessionId}/close")
    @FormUrlEncoded
    void close(@Path("sessionId") String sessionId, @Field("userId") String userId);

    @POST("sessions/{sessionId}/participations/enter")
    @FormUrlEncoded
    Call<Participation> participate(@Path("sessionId") String sessionId, @Field("userId") String userId) throws UserNotFoundException;

    @POST("sessions/{sessionId}/participations/quit")
    @FormUrlEncoded
    Call<Participation> quit(@Path("sessionId") String sessionId, @Field("userId") String userId) throws UserNotFoundException;

    @GET("presentations/{presId}/sessions")
    Call<List<PresSession>> list(@Path("presId") String presId);

}
