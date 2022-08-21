package com.as.XML;

import com.as.model.Korisnik;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class ReadXMLUsers {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        InputStream xmlInput  = new FileInputStream("fileUsersApp.xml");

        SAXParser saxParser = factory.newSAXParser();
        SaxHandlerTwo handler   = new SaxHandlerTwo();
        saxParser.parse(xmlInput, handler);

        for(Korisnik k : handler.korisnici()){
            System.out.println(k);
        }
    }

    static class SaxHandlerTwo extends DefaultHandler {
        private List<Korisnik> korisnikLinkedList = new LinkedList<>();

        private Korisnik trenutniClan;
        private String trenutniElement;

        public List<Korisnik> korisnici() { return korisnikLinkedList; }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            trenutniElement = qName;

            if ("Korisnici".equals(trenutniElement)) {
                trenutniClan = new Korisnik();
                return;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("Korisnik".equals(qName)) {
                korisnikLinkedList.add(trenutniClan);
                trenutniClan = null;
                return;
            }

            trenutniElement = null;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch, start, length).trim();
            if(value.length() == 0) return; // ignore white space

            switch(trenutniElement) {
                case "korisnickoIme" : trenutniClan.korisnickoIme = value; break;
                case "lozinka" : trenutniClan.lozinka = value; break;
                case "ponovljenaLozinka" : trenutniClan.lozinka = value; break;
            }
        }
    }
}
