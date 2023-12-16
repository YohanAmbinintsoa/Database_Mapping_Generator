package database;
import java.io.File;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Connector2 {
     public static Connection dbConnection(String database,String port,String user,String password,String driver,String databaseName,String ip,String type)throws Exception{
        String stringCon = "jdbc:"+type+"://"+ip+":"+port+"/"+databaseName;
        Connection con = null;
        try {
            System.out.println("tonga atooooo");
            Class.forName(driver);
            con = DriverManager.getConnection(stringCon,user,password);
            System.out.println(con);
            con.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Connection Failed");
        }
        return con;
    }
    public static String test (){
        return "nety";
    }

    public static Connection dbConnect(String type) throws Exception{
        String path = "./config.xml";
        Connection connection = null;
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("database");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String typeBase = element.getAttribute("jdbc");
                    if (typeBase.equalsIgnoreCase(type)) {
                        String username = element.getElementsByTagName("username").item(0).getTextContent();
                        String password = element.getElementsByTagName("password").item(0).getTextContent();
                        String databaseName = element.getElementsByTagName("databasename").item(0).getTextContent();
                        String driver = element.getElementsByTagName("driver").item(0).getTextContent();
                        String port = element.getElementsByTagName("port").item(0).getTextContent();
                        String ip = element.getElementsByTagName("ip").item(0).getTextContent();
                        System.out.println(databaseName + port+ username+ password+ driver+ databaseName+ ip);
                        connection = Connector2.dbConnection(databaseName, port, username, password, driver, databaseName, ip,type);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return connection;
    }

    
    public Connection postgresql(String user,String password,String database) throws Exception{
        Class.forName("org.postgresql.Driver");
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database,user,password);
        con.setAutoCommit(false);
        return con;
    }

    public Connection oracle(String user,String password,String database) throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:"+database, user, password);
        return con;
    }

    public static Connection ConnectToMySql()  throws Exception{
        String url = "jdbc:mysql://localhost/katsaka";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(url, "root", "root");
        con.setAutoCommit(false);
        return con;
    }

    public static Connection Connect(String jdbc,String user,String password,String database) throws Exception{
        Connector2 connect=new Connector2();
        Method met=connect.getClass().getMethod(jdbc, String.class,String.class,String.class);
        Connection con=(Connection)met.invoke(connect, user,password,database);
        return con;
    }

    public static String postgresSequence(String seqname){
        return "select nextval('"+seqname+"')";
    }

    public static String oracleSequence(String seqname){
        return seqname+".nextval";
    }

    public static String getSequenceSyntax(String jdbc,String seq_name){
        if (jdbc.equals("postgresql")) {
            return Connector2.postgresSequence(seq_name);
        } else if (jdbc.equals("oracle")) {
            return Connector2.oracleSequence(seq_name);
        }
        return "";
    }
}
