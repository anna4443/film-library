package com.as.model;

import java.io.Serializable;
import java.util.Date;

public class Overdue implements Serializable {

    private int memberId;
    private int movieId;
    private int charged;

    public Overdue(int memberId, int movieId, int charged) {
        this.memberId = memberId;
        this.movieId = movieId;
        this.charged = charged;
    }

    public Overdue() {
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCharged() {
        return charged;
    }

    public void setCharged(int charged) {
        this.charged = charged;
    }
}
