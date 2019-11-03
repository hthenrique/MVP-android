package com.example.mvpandroid.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmeResultadoBusca {
    @SerializedName("Search")
    public List<Filme> filmes;
    public List<FilmeDetalhes> filme;

    public FilmeResultadoBusca(){}

    public FilmeResultadoBusca(List<Filme> filmes){
        this.filmes = filmes;
    }

}
