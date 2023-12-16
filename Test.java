package test;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import database.Connector;


public class Test {
    

    public static void main(String[] args) {
        // Connection co = null;
        // try {
        //     co = Connector.dbConnect("postgresql");
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        System.out.println(Connector.test());
    }
}