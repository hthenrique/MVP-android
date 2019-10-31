package com.example.mvpandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class Filme {

        @SerializedName("imdbID")
        public String id;

        @SerializedName("Title")
        public String titulo;

        @SerializedName("Year")
        public String ano;

        @SerializedName("Director")
        public String diretor;

        @SerializedName("Poster")
        public String posterUrl;

        public Filme(){

        }

        public Filme(String id, String titulo, String ano, String diretor, String posterUrl){
            this.id = id;
            this.titulo = titulo;
            this.ano = ano;
            this.diretor = diretor;
            this.posterUrl = posterUrl;
        }

    }

