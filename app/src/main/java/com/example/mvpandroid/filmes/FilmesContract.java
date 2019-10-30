package com.example.mvpandroid.filmes;

import androidx.annotation.NonNull;

import com.example.mvpandroid.data.model.Filme;

import java.util.List;

public interface FilmesContract {

    interface View {
        void setCarregando(boolean isAtivo);

        void exibirFilmes(List<Filme> filmes);

        void exibirDetalhesUI (String filmesId);
    }

    interface UserActionsListener{

        void carregarFilmes();

        void abrirDetalhes(@NonNull Filme filme);

        void filtrarFilmes(String filter);
    }

}
