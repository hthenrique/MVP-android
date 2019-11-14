package com.example.mvpandroid.filmes;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.mvpandroid.data.FilmeServiceApi;

import com.example.mvpandroid.data.FilmeServiceImpl;
import com.example.mvpandroid.data.model.FilmeDetalhes;

public class FilmesPresenter implements FilmesContract.UserActionsListener {
    private final FilmeServiceApi mApi;
    private final FilmesContract.View mFilmesView;
    Context context;

    public FilmesPresenter(FilmesContract.View filmesView) {
        mApi = new FilmeServiceImpl(context);
        mFilmesView = filmesView;
    }

    //Busca destalhes através do ID
    @Override
    public void abrirDetalhes(@NonNull FilmeDetalhes filme) {
        mApi.getFilme(filme.imdbid, filmes -> mFilmesView.exibirDetalhesUI(filme));
    }


    //Carrega filmes pesquisados
    @Override
    public void carregarFilmes(String FilmeNome) {
        mFilmesView.setCarregando(false);
        if (FilmeNome == null){
            FilmeNome = "Spider-man";
            mApi.getPesquisa(FilmeNome, resultadoBusca -> {
                mFilmesView.setCarregando(false);
                mFilmesView.exibirFilmes(resultadoBusca.filme);
            });

        }else {
            mApi.getPesquisa(FilmeNome, resultadoBusca -> {
                mFilmesView.setCarregando(false);
                mFilmesView.exibirFilmes(resultadoBusca.filme);
            });
        }
    }

}
