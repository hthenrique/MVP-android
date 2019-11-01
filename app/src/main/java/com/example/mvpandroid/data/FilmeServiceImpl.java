package com.example.mvpandroid.data;

import android.widget.SearchView;

import com.example.mvpandroid.data.model.Filme;
import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.data.model.FilmeResultadoBusca;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmeServiceImpl implements FilmeServiceApi {

    RetrofitEndpoint mRetrofit;

    public FilmeServiceImpl() {
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }


    @Override
    public void getFilmes(final String titulo, final FilmeServiceCallback<FilmeResultadoBusca> callback) {
        Call<FilmeResultadoBusca> callFilmes = mRetrofit.busca(titulo, "json");
        callFilmes.enqueue(new Callback<FilmeResultadoBusca>() {
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
    public void getFilmes(final FilmeServiceCallback<FilmeResultadoBusca> callback) {
        Call<FilmeResultadoBusca> callFilmes = mRetrofit.busca("Spider-man","json");
        callFilmes.enqueue(new Callback<FilmeResultadoBusca>() {
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
    public void getFilme(final String filmeId,final FilmeServiceCallback<Filme> callBack) {
        Call<FilmeResultadoBusca> callFilmes = mRetrofit.busca(filmeId,"json");
        callFilmes.enqueue(new Callback<FilmeResultadoBusca>() {
            @Override
            public void onResponse(Call<FilmeResultadoBusca> call, Response<FilmeResultadoBusca> response) {
                if (response.code()==200){
                    FilmeResultadoBusca resultadoBusca = response.body();

                    Filme resultFilme = new Filme();
                    resultFilme.titulo = resultadoBusca.filmes.get(0).titulo;

                    callBack.onLoaded(resultFilme);
                }
            }

            @Override
            public void onFailure(Call<FilmeResultadoBusca> call, Throwable t) {

            }
        });
    }

    @Override
    public void getFilmeDt(String title, final FilmeServiceCallback<FilmeDetalhes> callback) {
        Call<FilmeResultadoBusca> callFilmes = mRetrofit.busca(title, "json");
        callFilmes.enqueue(new Callback<FilmeResultadoBusca>() {
            @Override
            public void onResponse(Call<FilmeResultadoBusca> call, Response<FilmeResultadoBusca> response) {
                if (response.code()==200){
                    FilmeResultadoBusca resultadoBusca = response.body();

                    FilmeDetalhes resultFilme = new FilmeDetalhes();
                    resultFilme.title = resultadoBusca.filmes.get(0).titulo;

                    callback.onLoaded(resultFilme);
                }
            }

            @Override
            public void onFailure(Call<FilmeResultadoBusca> call, Throwable t) {

            }
        });
    }


}
