package com.as;

import com.as.bl.SqlHandler;
import com.as.dal.DataSourceSingleton;
import com.as.model.Loan;
import com.as.model.Member;
import com.as.model.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.DataFormat;

import javax.activation.DataSource;
import java.net.DatagramSocket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UnosPosudbeController implements Initializable {

    @FXML
    private ComboBox cbFilmovi;

    @FXML
    private ComboBox cbClanovi;

    @FXML
    private DatePicker datumPosudbe;

    @FXML
    private DatePicker datumPovratka;

    private static final SqlHandler SH = new SqlHandler();

    @FXML
    public void dodajPosudbu(ActionEvent actionEvent) {
        try {
            Movie m = (Movie)cbFilmovi.getValue();
            Member mb = (Member)cbClanovi.getValue();

            java.util.Date datepos =
                    java.util.Date.from(datumPosudbe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDatepos = new java.sql.Date(datepos.getTime());

            java.util.Date datepov =
                    java.util.Date.from(datumPovratka.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDatepov = new java.sql.Date(datepov.getTime());

            Loan l = new Loan();

              l.setMovieId(m.getIdMovie());
              l.setMemeberId(mb.getIdMember());
              l.setDateLoan(sqlDatepos);
              l.setDateBack(sqlDatepov);

              SH.insertLoan(l);
              Main.loans.add(l);
            SerilizationExample.writeLoans(Main.loans);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspješno uneseni podaci!");
            alert.showAndWait();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Pogrešno uneseni podaci!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadMoviesAndMembers();
    }

    private void loadMoviesAndMembers()
    {
        cbFilmovi.setItems(SH.getMovies());
        cbClanovi.setItems(SH.getMembers());
    }
}
