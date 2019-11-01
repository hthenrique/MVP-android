package com.example.mvpandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class FilmeDetalhes {

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Rated")
    public String rated;

    @SerializedName("Released")
    public String released;

    @SerializedName("Runtime")
    public String runtime;

    @SerializedName("Genre")
    public String genre;

    @SerializedName("Director")
    public String director;

    @SerializedName("Writer")
    public String writer;

    @SerializedName("Actors")
    public String actors;

    @SerializedName("Plot")
    public String plot;

    @SerializedName("Language")
    public String language;

    @SerializedName("Country")
    public String country;

    @SerializedName("Awards")
    public String awards;

    @SerializedName("Poster")
    public String poster;

    @SerializedName("Ratings")
    public String ratings;

    @SerializedName("Metascore")
    public String metascore;

    @SerializedName("imdbRating")
    public String imdbrating;

    @SerializedName("imdbVotes")
    public String imdbvotes;

    @SerializedName("imdbID")
    public String imdbid;

    @SerializedName("Type")
    public String type;

    @SerializedName("DVD")
    public String dvd;

    @SerializedName("BoxOffice")
    public String boxoffice;

    @SerializedName("Production")
    public String prodution;

    @SerializedName("Website")
    public String website;

    @SerializedName("Response")
    public String response;

    public FilmeDetalhes(){

    }

    public FilmeDetalhes(String title, String year, String rated, String released,
                         String runtime, String genre, String director, String writer,
                         String actors, String plot, String language, String country,
                         String awards, String poster, String ratings, String metascore,
                         String imdbrating, String imdbvotes, String imdbid, String type,
                         String dvd, String boxoffice, String prodution, String website, String response){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.imdbid = imdbid;
        this.runtime = runtime;


    }

}
