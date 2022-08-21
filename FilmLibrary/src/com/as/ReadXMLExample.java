package com.as;

import com.as.model.Member;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReadXMLExample {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        InputStream xmlInput  = new FileInputStream("fileMembers.xml");

        SAXParser saxParser = factory.newSAXParser();
        SaxHandler handler   = new SaxHandler();
        saxParser.parse(xmlInput, handler);

        for(Member m : handler.members()){
            System.out.println(m);
        }
    }

     static class SaxHandler extends DefaultHandler {
        private List<Member> memberLinkedList = new LinkedList<>();

        private Member trenutniClan;
        private String trenutniElement;

        public List<Member> members() { return memberLinkedList; }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            trenutniElement = qName;

            if ("Members".equals(trenutniElement)) {
                trenutniClan = new Member();
                return;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("Member".equals(qName)) {
                memberLinkedList.add(trenutniClan);
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
                case "name" : trenutniClan.name = value; break;
                case "permanentAddress" : trenutniClan.permanentAddress = value; break;
                case "telephone" : trenutniClan.telephone = value; break;
            }
        }
    }
}
