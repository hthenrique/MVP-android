package com.example.mvpandroid.data.model;


import com.google.gson.annotations.SerializedName;

public class Imagens {
    @SerializedName("dribbbleID")
    public String id;

    @SerializedName("Titulo")
    public String titulo;

    @SerializedName("Likes")
    public String likes;

    @SerializedName("Poster")
    public String posterUrl;

    public Imagens(){

    }

    public Imagens(String id, String titulo, String likes, String posterUrl){
        this.id = id;
        this.titulo = titulo;
        this.likes = likes;
        this.posterUrl = posterUrl;
    }

}

