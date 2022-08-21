package com.as;

import com.as.bl.SqlHandler;
import com.as.model.Member;
import com.as.model.Movie;
import com.as.model.Overdue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UnosZakasnineController implements Initializable {

    @FXML
    private ComboBox cbMembers;

    @FXML
    private ComboBox cbMovies;

    @FXML
    private TextField txtCharged;

    @FXML
    private Button gumbDodajZakasninu;

    private static final SqlHandler SH = new SqlHandler();

    public void dodajZakasninu(ActionEvent actionEvent) {
        try {
            Movie m = (Movie)cbMovies.getValue();
            Member mb = (Member)cbMembers.getValue();

            Overdue o = new Overdue();

            o.setMemberId(mb.getIdMember());
            o.setMovieId(m.getIdMovie());
            o.setCharged(Integer.valueOf(txtCharged.getText()));

            SH.insertOverdue(o);
            Main.overdues.add(o);
            SerilizationExample.writeOverdues(Main.overdues);
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

    private void loadMoviesAndMembers() {

        cbMovies.setItems(SH.getMovies());
        cbMembers.setItems(SH.getMembers());
    }
}
