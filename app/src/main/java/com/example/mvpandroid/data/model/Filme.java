package com.example.mvpandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class Filme {

        @SerializedName("imdbID")
        public String id;

        @SerializedName("Title")
        public String titulo;

        @SerializedName("Year")
        public String ano;

        @SerializedName("Poster")
        public String posterUrl;

        public Filme(){

        }

}

