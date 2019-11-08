package com.example.mvpandroid.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    public List<Ratings> ratings;

    @SerializedName("Metascore")
    public String metascore;

    @SerializedName("imdbRating")
    public double imdbrating;

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
    public Boolean response;

    public FilmeDetalhes(){

    }

}
