package com.example.mvpandroid.filmes;

import androidx.annotation.NonNull;

import com.example.mvpandroid.data.model.FilmeDetalhes;

import java.util.List;

public interface FilmesContract {

    interface View {
        void setCarregando(boolean isAtivo);

        void exibirFilmes(List<FilmeDetalhes> filmes);

        void exibirDetalhesUI (FilmeDetalhes filme);

    }

    interface UserActionsListener{


        void abrirDetalhes(@NonNull FilmeDetalhes filme);

        void carregarFilmes(String FilmeNome);

    }

}
