package com.as;

import com.as.bl.SqlHandler;
import com.as.model.Movie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.net.URL;
import java.util.ResourceBundle;

public class UnosFilmaController implements Initializable {

    @FXML
    private TextField txtNaziv;

    @FXML
    private TextField txtMedij;

    @FXML
    private TextField txtTrajanje;

    @FXML
    private TextField txtRedatelj;

    @FXML
    private TextField txtGlavniGlumac;

    @FXML
    private TextField txtZanr;

    @FXML
    private TextArea txtaKratakOpis;

public static boolean unosUTijeku=false;
    private static final SqlHandler SH = new SqlHandler();

    private static boolean filmSpremanNaSpremanje=false;

    @FXML
    public void dodajFilm(ActionEvent actionEvent) {
Thread thread= new Thread(new Runnable() {
    @Override
    public void run() {
        dodavanjeFilma();
    }
});

thread.start();

    }

    private synchronized void dodavanjeFilma()  {
        while (unosUTijeku) {

            try {
                System.out.println("Drugi korisnik dodaje film.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        unosUTijeku=true;


            Movie m = new Movie();

            m.setName(txtNaziv.getText());
            m.setMedia(txtMedij.getText());
            m.setDuration(Integer.valueOf(txtTrajanje.getText()));
            m.setDirector(txtRedatelj.getText());
            m.setLeadingActor(txtGlavniGlumac.getText());
            m.setGenre(txtZanr.getText());
            m.setDescription(txtaKratakOpis.getText());

            SH.insertMovie(m);
            Main.movies.add(m);
            SerilizationExample.writeMovieSerialised(Main.movies);

            System.out.println("Uspjesno unesen film.");
            System.out.println("thread sleep");

            notifyAll();
        unosUTijeku=false;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Dodavanje filma je moguce.");
            //((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
