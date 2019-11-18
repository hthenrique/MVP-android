package com.example.mvpandroid.data;

import android.content.Context;
import android.widget.Toast;

import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.data.model.FilmeResultadoBusca;
import com.example.mvpandroid.detalhes.DetalhesActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmeServiceImpl implements FilmeServiceApi {

    RetrofitEndpoint mRetrofit;
    Context context;

    public FilmeServiceImpl(Context context) {
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    /*@Override
    public void getFilmes(final FilmeServiceCallback<FilmeResultadoBusca> callback) {
        Call<FilmeResultadoBusca> callFilmes = mRetrofit.busca2("2019","json");
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
    }*/

    @Override
    public void getFilme(String imdbid, final FilmeServiceCallback<FilmeDetalhes> callBack) {
        Call<FilmeDetalhes> callFilme = mRetrofit.buscaDetalhes(imdbid,"json");
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
                Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getPesquisa(String FilmeNome, final FilmeServiceCallback<FilmeResultadoBusca> callback) {
        Call<FilmeResultadoBusca> callFilme = mRetrofit.busca(FilmeNome.trim(),"json");
        callFilme.enqueue(new Callback<FilmeResultadoBusca>() {
            @Override
            public void onResponse(Call<FilmeResultadoBusca> call, Response<FilmeResultadoBusca> response) {

                try{
                    if (response.code()==200){
                        FilmeResultadoBusca resultadoBusca = response.body();
                        callback.onLoaded(resultadoBusca);
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Nenhum Resultado", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FilmeResultadoBusca> call, Throwable t) {
                Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
