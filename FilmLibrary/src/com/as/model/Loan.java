package com.as.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Loan implements Serializable {

    private int movieId;
    private int memeberId;
    private Date dateLoan;
    private Date dateBack;

    public Loan(int movieId, int memeberId, Date dateLoan, Date dateBack) {
        this.movieId = movieId;
        this.memeberId = memeberId;
        this.dateLoan = dateLoan;
        this.dateBack = dateBack;
    }

    public Loan() {
    }

    public Date getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(Date dateLoan) {
        this.dateLoan = dateLoan;
    }

    public Date getDateBack() {
        return dateBack;
    }

    public void setDateBack(Date dateBack) {
        this.dateBack = dateBack;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getMemeberId() {
        return memeberId;
    }

    public void setMemeberId(int memeberId) {
        this.memeberId = memeberId;
    }


}
