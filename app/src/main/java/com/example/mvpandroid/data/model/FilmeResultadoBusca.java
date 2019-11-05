package com.example.mvpandroid.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmeResultadoBusca {
    @SerializedName("Search")
    public List<FilmeDetalhes> filme;

}
