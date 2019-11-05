package com.example.mvpandroid.detalhes;


import com.example.mvpandroid.data.model.FilmeDetalhes;

public class DetalhesContract {
    interface View {
        void exibirDetalhes(FilmeDetalhes filmeDetalhes);
    }

    interface Presenter{
        void carregarFilme(String imdbid);
    }

    interface UserActionsListener {
    }
}
