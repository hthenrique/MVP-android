package com.example.mvpandroid.filmes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mvpandroid.data.FilmeServiceApi;

import com.example.mvpandroid.data.model.Filme;
import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.data.model.FilmeResultadoBusca;

public class FilmesPresenter implements FilmesContract.UserActionsListener {
    private final FilmeServiceApi mApi;
    private final FilmesContract.View mFilmesView;

    private static final String TAG = "FilmesPresenter";

    public FilmesPresenter(FilmeServiceApi api, FilmesContract.View filmesView) {
        mApi = api;
        mFilmesView = filmesView;
    }

    @Override
    public void carregarFilmes() {
        mFilmesView.setCarregando(true);

        mApi.getFilmes(new FilmeServiceApi.FilmeServiceCallback<FilmeResultadoBusca>() {
            @Override
            public void onLoaded(FilmeResultadoBusca resultadoBusca) {
                mFilmesView.setCarregando(false);
                mFilmesView.exibirFilmes(resultadoBusca.filmes);
            }
        });
    }

    @Override
    public void abrirDetalhes(@NonNull final Filme filme) {
        mApi.getFilme(filme.id, new FilmeServiceApi.FilmeServiceCallback<FilmeDetalhes>() {
            @Override
            public void onLoaded(FilmeDetalhes filmes) {
                mFilmesView.exibirDetalhesUI(filmes);
            }
        });
    }

    @Override
    public void carregarFilmes(String query) {

    }

}
