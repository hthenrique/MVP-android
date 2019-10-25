package com.example.mvpandroid.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagensResultadoBusca {
    @SerializedName("Search")
    public List<Imagens> imagens;

    public ImagensResultadoBusca(){}

    public ImagensResultadoBusca(List<Imagens> imagens){
        this.imagens = imagens;
    }
}
