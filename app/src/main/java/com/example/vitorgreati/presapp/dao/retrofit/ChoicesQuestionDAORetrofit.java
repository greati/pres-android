package com.example.vitorgreati.presapp.dao.retrofit;

import com.example.vitorgreati.presapp.model.ChoicesAnswer;
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

public interface ChoicesQuestionDAORetrofit {

    @POST("questions")
    Call<ChoicesQuestion> create(@Body ChoicesQuestion q);

    @PUT("questions")
    Call<ChoicesQuestion> update(@Body ChoicesQuestion q);

    @DELETE("sessions/{sessionId}/questions")
    void delete(@Body ChoicesQuestion q);

    @POST("sessions/{sessionId}/questions/{questionId}/open")
    void open(@Path("sessionId") String sessionId, @Path("questionId") String questionId);

    @POST("sessions/{sessionId}/questions/{questionId}/close")
    void close(@Path("sessionId") String sessionId, @Path("questionId") String questionId);

    @GET("sessions/{sessionId}/questions")
    Call<List<ChoicesQuestion>> list(@Path("sessionId") String sessionId);

    @POST("sessions/{sessionId}/questions/{questionId}")
    Call<ChoicesQuestion> get(@Path("questionId") String questionId);

    @POST("sessions/{sessionId}/questions/{questionId}")
    @FormUrlEncoded
    Call<ChoicesAnswer> answer(@Path("questionId") String questionId, @Field("userId") String userId);
}
