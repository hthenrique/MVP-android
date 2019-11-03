package com.example.mvpandroid.filmes;

import androidx.annotation.NonNull;

import com.example.mvpandroid.data.model.Filme;
import com.example.mvpandroid.data.model.FilmeDetalhes;

import java.util.List;

public interface FilmesContract {

    interface View {
        void setCarregando(boolean isAtivo);

        void exibirFilmes(List<Filme> filmes);

        void exibirDetalhesUI (FilmeDetalhes filme);
    }

    interface View2 {

        void setCarregando(boolean isAtivo);

        void exibirFilmes(List<FilmeDetalhes> filme);

        void exibirDetalhesUI (FilmeDetalhes filme);

    }

    interface UserActionsListener{

        void carregarFilmes();

        void abrirDetalhes(@NonNull Filme filme);

        void carregarFilmes(String query);
    }

}
