package com.example.crud05;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdaptadorRetrofit {
    Retrofit retrofit;

    public AdaptadorRetrofit() {
    }

    public Retrofit getAdapatador(){
        retrofit=new Retrofit.Builder()
                .baseUrl("http://roormago96.pythonanywhere.com/cafeteria/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
