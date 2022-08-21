package com.as;

import com.as.model.Korisnik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class EkranZaRegistracijuController implements Initializable {

    @FXML
    private TextField korisnickoImeTextField;

    @FXML
    private PasswordField lozinkaPasswordField;

    @FXML
    private PasswordField ponovljenaLozinkaPasswordTextField;


    @FXML
    private void pritisakGumbaRegistracija(ActionEvent event) throws ParserConfigurationException, TransformerException {

        if (!lozinkaPasswordField.getText().equals(ponovljenaLozinkaPasswordTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pogreška kod unošenja podataka!");
            alert.setHeaderText("Neispravni podaci!");
            alert.setContentText("Lozinka i ponovljena lozinka nisu iste!");

            alert.showAndWait();
        } else {
            Korisnik noviKorisnik = new Korisnik(korisnickoImeTextField.getText(),
                    lozinkaPasswordField.getText());

            for(Korisnik korisnik : Main.repozitorijKorisnika) {
                if(korisnik.getKorisnickoIme().equals(noviKorisnik.getKorisnickoIme())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Pogreška!");
                    alert.setHeaderText("Neuspješno registriran korisnik!");
                    alert.setContentText("Unijeli ste korisničko ime koje već postoji!");

                    alert.showAndWait();
                    return;
                }
            }

            Main.repozitorijKorisnika.add(noviKorisnik);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Akcija uspješno izvršena!");
            alert.setHeaderText("Uspješno registriran korisnik!");
            alert.setContentText("Uneseni korisnik je spremljen u sustav!"
                    + "\nMožete se prijavi s njime!");

            Document xmlDoc = createDocumentForUsers();
            saveDocUsers(xmlDoc, "fileUsersApp.xml");

            alert.showAndWait();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    private void saveDocUsers(Document xmlDoc, String fileName) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(xmlDoc);
        StreamResult result = new StreamResult(new File("fileUsersApp.xml"));

        transformer.transform(source, result);
        transformer.transform(source, new StreamResult(System.out));
    }

    private Document createDocumentForUsers() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        Element korisnici = doc.createElement("Korisnici");

        List<Korisnik> korisnikList = getKorisnik();

        for(Korisnik k : korisnikList) {
            Element korisnikElement = createNewKorisnikElement(doc, k);
            korisnici.appendChild(korisnikElement);
        }

        doc.appendChild(korisnici);
        return doc;
    }

    private Element createNewKorisnikElement(Document doc, Korisnik k) {
        Element korisnik = doc.createElement("Korisnik");

        Element korisnickoIme = doc.createElement("korisnickoIme");
        korisnickoIme.appendChild(doc.createTextNode(korisnickoImeTextField.getText()));
        korisnik.appendChild(korisnickoIme);

        Element lozinka = doc.createElement("lozinka");
        lozinka.appendChild(doc.createTextNode(lozinkaPasswordField.getText()));
        korisnik.appendChild(lozinka);

        Element ponovoljenaLozinka = doc.createElement("ponovljenaLozinka");
        ponovoljenaLozinka.appendChild(doc.createTextNode(ponovljenaLozinkaPasswordTextField.getText()));
        korisnik.appendChild(ponovoljenaLozinka);

        return  korisnik;
    }

    private List<Korisnik> getKorisnik() {
        Korisnik k = new Korisnik();
        
        List<Korisnik> korisnici = new LinkedList<>();
        
        korisnici.add(k);
        
        return korisnici;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
