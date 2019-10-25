package com.example.mvpandroid.data;

import com.example.mvpandroid.data.model.ImagensResultadoBusca;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitEndpoint {
    @GET("./")
    Call<ImagensResultadoBusca> busca(@Query("s") String q, @Query("r") String format);
}
