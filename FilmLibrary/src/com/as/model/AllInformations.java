package com.as.model;

import java.io.Serializable;

public class AllInformations implements Serializable {

    private Korisnik korisnik;
    private Loan loan;
    private Member member;
    private Movie movie;
    private Overdue overdue;

    public AllInformations(Korisnik korisnik, Loan loan, Member member, Movie movie, Overdue overdue) {
        this.korisnik = korisnik;
        this.loan = loan;
        this.member = member;
        this.movie = movie;
        this.overdue = overdue;
    }

    public AllInformations() {
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Overdue getOverdue() {
        return overdue;
    }

    public void setOverdue(Overdue overdue) {
        this.overdue = overdue;
    }
}
