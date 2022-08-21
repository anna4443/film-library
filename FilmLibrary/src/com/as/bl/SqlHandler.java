package com.as.bl;

import com.as.model.Loan;
import com.as.model.Member;
import com.as.model.Movie;
import com.as.model.Overdue;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

public class SqlHandler extends HandlerBase{

    public void insertMovie(Movie m){ repository.insertMovie(m);}
    public void insertMember(Member mb) {repository.insertMember(mb);}
    public void insertLoan(Loan l){repository.insertLoan(l);}
    public void insertOverdue(Overdue o){repository.insertOverdue(o);}
    public ObservableList<Movie> getMovies(){return repository.getMovies();}
    public ObservableList<Member> getMembers(){return repository.getMembers();}
}
