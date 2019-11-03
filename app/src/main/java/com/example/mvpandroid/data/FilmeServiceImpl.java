package com.example.mvpandroid.data;

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
    public void getFilme(String filmeId, final FilmeServiceCallback<FilmeDetalhes> callBack) {
        Call<FilmeDetalhes> callFilme = mRetrofit.buscaDetalhes(filmeId,"json");
        callFilme.enqueue(new Callback<FilmeDetalhes>() {
            @Override
            public void onResponse(Call<FilmeDetalhes> call, Response<FilmeDetalhes> response) {
                if (response.code()==200){
                    FilmeDetalhes filme = response.body();
                    callBack.onLoaded(filme);
                }
            }

            @Override
            public void onFailure(Call<FilmeDetalhes> call, Throwable t) {

            }
        });
    }

    @Override
    public void getPesquisa(String Filme, final FilmeServiceCallback<FilmeResultadoBusca> callback) {

        Call<FilmeResultadoBusca> callFilme = mRetrofit.busca(Filme,"json");
        callFilme.enqueue(new Callback<FilmeResultadoBusca>() {
            @Override
            public void onResponse(Call<FilmeResultadoBusca> call, Response<FilmeResultadoBusca> response) {
                if (response.code()==200){
                    FilmeResultadoBusca resultadoBusca =response.body();
                    callback.onLoaded(resultadoBusca);
                }
            }

            @Override
            public void onFailure(Call<FilmeResultadoBusca> call, Throwable t) {

            }
        });

    }


}
