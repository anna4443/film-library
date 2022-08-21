package com.as;

import com.as.model.Korisnik;
import com.as.model.Movie;
import com.as.niti.Clock;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField korisnickoImeTextField;

    @FXML
    private TextField lozinkaPasswordField;

    @FXML
    private VBox vb;

    @FXML
    private void pritisakGumbaPrijava (ActionEvent event) throws ParserConfigurationException, TransformerException {
       korisnickoImeTextField.getStyleClass().remove("error");
       lozinkaPasswordField.getStyleClass().remove("error");

        StringBuilder builder = new StringBuilder();
        boolean hasErrors = false;

        if (korisnickoImeTextField.getText().isEmpty()) {
            builder.append("Niste unijeli korisničko ime!\n");
            hasErrors = true;
            korisnickoImeTextField.getStyleClass().add("error");
        }

        if (lozinkaPasswordField.getText().isEmpty()) {
            builder.append("Niste unijeli lozinku!\n");
            hasErrors = true;
            lozinkaPasswordField.getStyleClass().add("error");
        }

        if(Main.repozitorijKorisnika.isEmpty()) {
            hasErrors = true;
            builder.append("Nema nijednog registriranog korisnika, molimo kreirajte barem jednog korisnika!");
        }

        if (!korisnickoImeTextField.getText().isEmpty() && !lozinkaPasswordField.getText().isEmpty()) {
            for (Korisnik korisnik : Main.repozitorijKorisnika) {
                if (!korisnik.getKorisnickoIme().equals(korisnickoImeTextField.getText())
                        || !korisnik.getLozinka().equals(lozinkaPasswordField.getText())) {
                    builder.append("Unijeli ste neispravnog korisnika i/ili lozinku!");
                    hasErrors = true;
                } else {
                    hasErrors = false;
                    break;
                }
            }
        }

        if (hasErrors) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pogreška kod unošenja podataka!");
            alert.setHeaderText("Neispravni podaci!");
            alert.setContentText(builder.toString());

            alert.showAndWait();
        } else {

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("DrugiEkran.fxml"));
                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Platform.runLater(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    stage.hide();
                });


                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    if (korisnickoImeTextField.getText().equals("pero") && lozinkaPasswordField.getText().equals("pero"))
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("EkranZaAdmina.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            GridPane gridPane = new GridPane();
            gridPane.getStyleClass().addAll("grid", "gridPane");

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    else
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("EkranZaUsera.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            GridPane gridPane = new GridPane();
            gridPane.getStyleClass().addAll("grid", "gridPane");

            Document xmlDoc = createDocumentLogIn();
            saveDocLogin(xmlDoc, "fileLogin.xml");

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

    private void saveDocLogin(Document xmlDoc, String fileName) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(xmlDoc);
        StreamResult result = new StreamResult(new File("fileLogin.xml"));

        transformer.transform(source, result);
        transformer.transform(source, new StreamResult(System.out));
    }

    private Document createDocumentLogIn() throws ParserConfigurationException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        Element korisnici = doc.createElement("Korisnici");

        List<Korisnik> korisnikList = getKorisnik();

        for(Korisnik k : korisnikList) {
            Element korisnikElement = createKorisnikElement(doc, k);
            korisnici.appendChild(korisnikElement);
        }

        doc.appendChild(korisnici);
        return doc;
    }

    private Element createKorisnikElement(Document doc, Korisnik k) {
        Element korisnik = doc.createElement("Korisnik");

        Element korisnickoIme = doc.createElement("korisnickoIme");
        korisnickoIme.appendChild(doc.createTextNode(korisnickoImeTextField.getText()));
        korisnik.appendChild(korisnickoIme);

        Element lozinka = doc.createElement("lozinka");
        lozinka.appendChild(doc.createTextNode(lozinkaPasswordField.getText()));
        korisnik.appendChild(lozinka);

        return  korisnik;
    }

    private List<Korisnik> getKorisnik() {
        Korisnik k = new Korisnik();

        List<Korisnik> korisnici = new LinkedList<>();

        korisnici.add(k);

        return korisnici;
    }

    @FXML
    public void pritisakGumbaRegistracija(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("EkranZaRegistraciju.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Korisnik noviKorisnik = new Korisnik("pero", "pero");
        Main.repozitorijKorisnika.add(noviKorisnik);

        Label currentTimeThread = new Label();
        Label currentTimeTask = new Label();

        Clock.startThreadedClock(currentTimeThread);
        Clock.startClockTask(currentTimeTask);

        vb.getChildren().addAll(currentTimeThread, currentTimeTask);
    }
}
