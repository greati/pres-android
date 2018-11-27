package com.example.vitorgreati.presapp.dao.retrofit;

import com.example.vitorgreati.presapp.model.ChoicesQuestion;
import com.example.vitorgreati.presapp.model.PresSession;

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

public interface QuestionDAORetrofit {

    @POST("sessions/{sessionId}/questions")
    void create(@Body ChoicesQuestion q);

    @PUT("sessions/{sessionId}/questions")
    void update(@Body ChoicesQuestion q);

    @DELETE("sessions/{sessionId}/questions")
    void delete(@Body ChoicesQuestion q);

    @POST("sessions/{sessionId}/questions/{questionId}/open")
    void open(@Path("sessionId") String sessionId, @Path("questionId") String questionId);

    @POST("sessions/{sessionId}/questions/{questionId}/close")
    void close(@Path("sessionId") String sessionId, @Path("questionId") String questionId);

    @GET("sessions/{sessionId}/questions")
    Call<List<ChoicesQuestion>> list(@Path("sessionId") String sessionId);

    @POST("sessions/{sessionId}/questions/{questionId}")
    void get(@Path("questionId") String questionId);

    @POST("sessions/{sessionId}/questions/{questionId}")
    @FormUrlEncoded
    void answer(@Path("questionId") String questionId, @Field("userId") String userId);
}
