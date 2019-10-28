package com.example.mvpandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class Filme {

        @SerializedName("imdbID")
        public String id;

        @SerializedName("Titulo")
        public String titulo;

        @SerializedName("Ano")
        public String likes;

        @SerializedName("Poster")
        public String posterUrl;

        public Filme(){

        }

        public Filme(String id, String titulo, String ano, String posterUrl){
            this.id = id;
            this.titulo = titulo;
            this.likes = ano;
            this.posterUrl = posterUrl;
        }

    }

