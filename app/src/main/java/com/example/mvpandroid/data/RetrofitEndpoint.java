package com.example.mvpandroid.data;

import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.data.model.FilmeResultadoBusca;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitEndpoint {
    @GET("/?apikey=5b78ff52&")
    Call<FilmeResultadoBusca> busca(@Query("s") String q, @Query("r") String format);

    @GET("/?apikey=5b78ff52&")
    Call<FilmeResultadoBusca> busca2(@Query("y") String q, @Query("r") String format);

    @GET("/?apikey=5b78ff52&")
    Call<FilmeDetalhes> buscaDetalhes(@Query("i") String q, @Query("r") String format);
}
