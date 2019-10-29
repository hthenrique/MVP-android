package com.example.mvpandroid.data;

import android.widget.SearchView;

import com.example.mvpandroid.data.model.Filme;
import com.example.mvpandroid.data.model.FilmeResultadoBusca;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmeServiceImpl implements FilmeServiceApi {
    RetrofitEndpoint mRetrofit;

    private SearchView searchView;



    public FilmeServiceImpl(SearchView searchView){
        this.searchView = searchView;
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    public FilmeServiceImpl() {
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    @Override
    public void getFilmes(final FilmeServiceCallback<FilmeResultadoBusca> callback) {
        Call<FilmeResultadoBusca> callImagens = mRetrofit.busca("Avengers","json");
        callImagens.enqueue(new Callback<FilmeResultadoBusca>() {
            @Override
            public void onResponse(Call<FilmeResultadoBusca> call, Response<FilmeResultadoBusca> response) {
                if (response.code()==200){
                    FilmeResultadoBusca resultadoBusca = response.body();
                    callback.onLoaded(resultadoBusca);
                }
            }

            @Override
            public void onFailure(Call<FilmeResultadoBusca> call, Throwable t) {

            }
        });
    }

    @Override
    public void getFilme(final String filmeId, FilmeServiceCallback<Filme> callBack) {

    }
}
