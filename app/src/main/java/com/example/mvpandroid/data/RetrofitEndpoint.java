package com.example.mvpandroid.data;

import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.data.model.FilmeResultadoBusca;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitEndpoint {
    @GET("/?apikey=5b78ff52&")
    Call<FilmeResultadoBusca> busca(@Query("s") String q, @Query("r") String format);

    @GET("/?apikey=5b78ff52&")
    Call<FilmeDetalhes> busca2(@Query("s") String q,@Query("page") int page, @Query("r") String format);

    @GET("/?apikey=5b78ff52&")
    Call<FilmeDetalhes> buscaDetalhes(@Query("i") String i, @Query("r") String format);

}
