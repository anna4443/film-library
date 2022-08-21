package com.as.model;

import javafx.collections.ObservableList;

import java.util.Random;

public class MovieUtility {
    public static void assignRateToRndMovie(ObservableList<Movie> movies)
    {
        Random rnd = new Random();

        int index = rnd.nextInt(movies.size());
        Movie movie = movies.get(index);

        int n = rnd.nextInt(10);
        System.out.println(movie.getName() + ": " + n);
    }
}
