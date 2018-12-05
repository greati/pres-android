package com.example.vitorgreati.presapp.dao.retrofit.provider;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private static Retrofit retrofit;

    private static RetrofitProvider provider;

    private RetrofitProvider() {
        retrofit = new Retrofit.Builder()
                //.baseUrl("http://pres-api.vitorgreati.webfactional.com")
                //.baseUrl("http://10.0.2.2:3000")
                .baseUrl("http://10.0.0.103:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitProvider getInstance() {
        if (provider == null)
            provider = new RetrofitProvider();
        return provider;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
