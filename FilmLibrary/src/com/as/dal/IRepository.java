package com.as.dal;

import com.as.model.Loan;
import com.as.model.Member;
import com.as.model.Movie;
import com.as.model.Overdue;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

public interface IRepository {

    void insertMovie(Movie m);
    void insertMember(Member mb);
    void insertLoan(Loan l);
    void insertOverdue(Overdue o);

    ObservableList<Movie> getMovies();
    ObservableList<Member> getMembers();
}
