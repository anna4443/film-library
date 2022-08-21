package com.as.dal;

import com.as.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SqlRepository implements  IRepository {

    private static final String INSERT_MOVIE = "{CALL InsertMovie(?,?,?,?,?,?,?)}";
    private static final String INSERT_MEMBER = "{CALL InsertMember(?,?,?)}";
    private static final String INSERT_LOAN = "{CALL InsertLoan(?,?,?,?)}";
    private static final String GET_MOVIES = "{CALL GetMovies }";
    private static final String GET_MEMBERS = "{CALL GetMembers}";
    private static final String INSERT_OVERDUE = "{CALL InsertOverdue(?,?,?)}";

    @Override
    public void insertMovie(Movie m) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(INSERT_MOVIE);
        ) {
            stmt.setString(1, m.getName());
            stmt.setString(2, m.getMedia());
            stmt.setInt(3, m.getDuration());
            stmt.setString(4, m.getDirector());
            stmt.setString(5, m.getLeadingActor());
            stmt.setString(6, m.getGenre());
            stmt.setString(7, m.getDescription());

            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertMember(Member mb) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(INSERT_MEMBER);
        ) {
            stmt.setString(1, mb.getName());
            stmt.setString(2, mb.getPermanentAddress());
            stmt.setString(3, mb.getTelephone());

            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertLoan(Loan l) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(INSERT_LOAN);
        ) {
            stmt.setInt(1, l.getMovieId());
            stmt.setInt(2, l.getMemeberId());
            stmt.setDate(3, new Date(l.getDateLoan().getTime()));
            stmt.setDate(4,new Date(l.getDateBack().getTime()));

            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOverdue(Overdue o) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(INSERT_OVERDUE);
        ) {
            stmt.setInt(1, o.getMemberId());
            stmt.setInt(2, o.getMovieId());
            stmt.setInt(3, o.getCharged());

            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Movie> getMovies() {
        DataSource dataSource = DataSourceSingleton.getInstance();
        List<Movie> list = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(GET_MOVIES)
        ) {
            while (rs.next()) {
                list.add(new Movie(rs.getInt(1), rs.getString(2)));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(list);
    }

    @Override
    public ObservableList<Member> getMembers() {
        DataSource dataSource = DataSourceSingleton.getInstance();
        List<Member> list = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(GET_MEMBERS)
        ) {
            while (rs.next()) {
                list.add(new Member(rs.getInt(1), rs.getString(2)));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(list);
    }

}
