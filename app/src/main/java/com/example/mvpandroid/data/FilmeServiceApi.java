package com.example.mvpandroid.data;


import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.data.model.FilmeResultadoBusca;

//View

public interface FilmeServiceApi {

    interface FilmeServiceCallback<T>{
        void onLoaded(T filmes);
    }

    void getFilme(String imdbid, FilmeServiceCallback<FilmeDetalhes> callback);

    void getPesquisa(String FilmeNome, FilmeServiceCallback<FilmeResultadoBusca> callback);

    //void getFilmes(FilmeServiceCallback<FilmeResultadoBusca> callback);

}
