package com.example.mvpandroid.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class RetrofitClient {
    public static final String BASE_URL = "https://dribbble.com/";
    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder()
            .create();

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
