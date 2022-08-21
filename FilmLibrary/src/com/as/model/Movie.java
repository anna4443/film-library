package com.as.model;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Movie implements Serializable {

    private int idMovie;
    private String name;
    private String media;
    private int duration;
    private String director;
    private String leadingActor;
    private String genre;
    private String description;

    public Movie() {
    }

    public Movie(int idMovie, String name, String media, int duration, String director, String leadingActor, String genre, String description) {
        this.idMovie = idMovie;
        this.name = name;
        this.media = media;
        this.duration = duration;
        this.director = director;
        this.leadingActor = leadingActor;
        this.genre = genre;
        this.description = description;
    }


    public Movie(String name, String media, String director, String leadingActor, String genre) {
        this.name = name;
        this.media = media;
        this.director = director;
        this.leadingActor = leadingActor;
        this.genre = genre;
    }
    public Movie(int idMovie, String name) {
        this.idMovie = idMovie;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLeadingActor() {
        return leadingActor;
    }

    public void setLeadingActor(String leadingActor) {
        this.leadingActor = leadingActor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
