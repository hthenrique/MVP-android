package com.example.mvpandroid.filmes;

import androidx.annotation.NonNull;

import com.example.mvpandroid.data.FilmeServiceApi;

import com.example.mvpandroid.data.FilmeServiceImpl;
import com.example.mvpandroid.data.model.FilmeDetalhes;

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
            mFilmesView.exibirFilmes(resultadoBusca.filme);
        });
    }


    @Override
    public void abrirDetalhes(@NonNull FilmeDetalhes filme) {
        mApi.getFilme(filme.imdbid, filmes -> mFilmesView.exibirDetalhesUI(filme));
    }

    @Override
    public void carregarFilmes(String FilmeNome) {
        mFilmesView.setCarregando(true);
        if (FilmeNome == null){
            FilmeNome = "Spider-man";
        }
        mApi.getPesquisa(FilmeNome, resultadoBusca -> {
            mFilmesView.setCarregando(false);
            mFilmesView.exibirFilmes(resultadoBusca.filme);
        });

    }

}
