package com.example.mvpandroid.data;


import com.example.mvpandroid.data.model.Filme;
import com.example.mvpandroid.data.model.FilmeResultadoBusca;

public interface FilmeServiceApi {

    //void getFilme(FilmeServiceCallback<FilmeResultadoBusca> filmeResultadoBuscaFilmeServiceCallback);

    interface FilmeServiceCallback<T>{
        void onLoaded(T filmes);
    }

    void getFilmes(String titulo, FilmeServiceCallback<FilmeResultadoBusca> callback);

    void getFilmes(FilmeServiceCallback<FilmeResultadoBusca> callback);

    void getFilme(String filmeId, FilmeServiceCallback<Filme> callback);
}
