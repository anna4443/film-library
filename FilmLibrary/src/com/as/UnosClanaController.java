package com.as;

import com.as.bl.SqlHandler;
import com.as.model.Member;
import com.as.model.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

public class UnosClanaController implements Initializable {

    @FXML
    private TextField txtImePrezime;

    @FXML
    private TextField txtAdresa;

    @FXML
    private TextField txtTelefon;

    private Movie m = null;

    public void setM(Movie m) {
        this.m = m;
    }

    private static final SqlHandler SH = new SqlHandler();

    @FXML
    public void dodajClana(ActionEvent actionEvent) {
        try {
            Member mb = new Member();

            mb.setName(txtImePrezime.getText());
            mb.setPermanentAddress(txtAdresa.getText());
            mb.setTelephone(txtTelefon.getText());

            SH.insertMember(mb);
           Main.members.add(mb);
           SerilizationExample.writeMemberSerialsed(Main.members);

//           createDocumentForMembers();

           Document xmlDoc = createDocumentForMembers();
           saveDocMb(xmlDoc, "fileMembers.xml");

//            Document xmlDocument =  XMLExample.createDocumentMembers();
//           XMLExample.saveDocumentMembers(xmlDocument, "fileMembers.xml");

//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uspješno uneseni podaci!");
//            alert.showAndWait();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Pogrešno uneseni podaci!");
            alert.showAndWait();
        }
    }

    private void saveDocMb(Document xmlDoc, String fileName) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(xmlDoc);
        StreamResult result = new StreamResult(new File("fileMembers.xml"));

        transformer.transform(source, result);
        transformer.transform(source, new StreamResult(System.out));
    }

    private Document createDocumentForMembers() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        Element members = doc.createElement("Members");

        List<Member> memberList = getMember();

        for(Member mb : memberList) {
            Element memberElement = createNewMemberElement(doc, mb);
            members.appendChild(memberElement);
        }

        doc.appendChild(members);
        return doc;
    }

    private Element createNewMemberElement(Document doc, Member mb) {
        Element member = doc.createElement("Member");

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(txtImePrezime.getText()));
        member.appendChild(name);

        Element permanentAddress = doc.createElement("permanentAddress");
        permanentAddress.appendChild(doc.createTextNode(txtAdresa.getText()));
        member.appendChild(permanentAddress);

        Element telephone = doc.createElement("telephone");
        telephone.appendChild(doc.createTextNode(txtTelefon.getText()));
        member.appendChild(telephone);

        return  member;
    }

    private List<Member> getMember() {
        Member mb = new Member();

        List<Member> members = new LinkedList<>();

        members.add(mb);

        return members;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
