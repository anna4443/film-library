package com.as;

import com.as.model.Member;
import com.as.model.Movie;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class XMLExample {

    public static void main(String[] args) throws ParserConfigurationException {

    }

    private static List<Member> getMember() {
        Member mb = new Member();

       List<Member> members = new LinkedList<>();

        members.add(mb);

        return members;
    }

    public static Document createDocumentMembers() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        // korijenski element
        Element members = doc.createElement("Members");

        // postavi movies kao glavni element


        List<Member> memberList = getMember();

        for(Member mb : memberList) {
            Element memberElement = createNewMemberElement(doc, mb);
            members.appendChild(memberElement);
        }

        doc.appendChild(members);
        return doc;
    }

    private static Element createNewMemberElement(Document doc, Member mb) {
        Element member = doc.createElement("Member");

        Element idMember = doc.createElement("idMember");
        idMember.appendChild(doc.createTextNode(String.valueOf(mb.idMember)));
        member.appendChild(idMember);

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(mb.name));
        member.appendChild(name);

        Element permanentAddress = doc.createElement("permanentAddress");
        permanentAddress.appendChild(doc.createTextNode(mb.permanentAddress));
        member.appendChild(permanentAddress);

        Element telephone = doc.createElement("telephone");
        telephone.appendChild(doc.createTextNode(mb.telephone));
        member.appendChild(telephone);

        return  member;
    }

    public static void saveDocumentMembers(Document xmlDocument, String nameFile) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(xmlDocument);
        StreamResult result = new StreamResult(new File("fileMembers.xml"));

        transformer.transform(source, result);
        transformer.transform(source, new StreamResult(System.out));
    }
}
