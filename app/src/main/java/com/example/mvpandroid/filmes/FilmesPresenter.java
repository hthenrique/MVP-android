package com.example.mvpandroid.filmes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mvpandroid.data.FilmeServiceApi;

import com.example.mvpandroid.data.FilmeServiceImpl;
import com.example.mvpandroid.data.model.Filme;
import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.data.model.FilmeResultadoBusca;

public class FilmesPresenter implements FilmesContract.UserActionsListener {
    private final FilmeServiceApi mApi;
    private final FilmesContract.View mFilmesView;

    public FilmesPresenter(FilmesContract.View filmesView) {
        mApi = new FilmeServiceImpl();
        mFilmesView = filmesView;
    }

    @Override
    public void carregarFilmes() {
        mFilmesView.setCarregando(true);

        mApi.getFilmes(resultadoBusca -> {
            mFilmesView.setCarregando(false);
            mFilmesView.exibirFilmes(resultadoBusca.filmes);
        });
    }


    @Override
    public void abrirDetalhes(@NonNull Filme filme) {
        mApi.getFilme(filme.id, filmes -> mFilmesView.exibirDetalhesUI(filmes.imdbid));
    }

    @Override
    public void carregarFilmes(String FilmeNome) {
        mFilmesView.setCarregando(true);
        if (FilmeNome == null){
            FilmeNome = "Spider-man";
        }
        mApi.getPesquisa(FilmeNome, resultadoBusca -> {
            mFilmesView.setCarregando(false);
            mFilmesView.exibirFilmes(resultadoBusca.filmes);
        });

    }

}
