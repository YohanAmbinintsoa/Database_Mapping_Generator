package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ColumnData;
import database.Connector2;
import utilities.Bootstrap;

public class TableInformation {
    String database;
    String language;
    String pack;
    String type;
   

    private String username="postgres";
    private String password="root";

    public TableInformation(String type,String database,String language,String pack){
        this.database=database;
        this.setLanguage(language);
        this.setPack(pack);
        this.setType(type);
    }


    public List<String> getAllTables() throws Exception{
        List<String> list=null;
        boolean nisokatra=false;
        Connection con = null;
        if (con==null) {
            con = Connector2.dbConnect(this.type);
            nisokatra=true;
        }
        list=new ArrayList<>();
        DatabaseMetaData metaData = con.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});
                
        while (resultSet.next()) {
            list.add(resultSet.getString("TABLE_NAME"));
        }
        resultSet.close();
        if (nisokatra==true) {
            con.close();
        }
        return list;
    }

    public List<ColumnData> getColumnData(String tableName) throws Exception{
        List<ColumnData> list=null;
        boolean nisokatra=false;
        Connection con = null;
        if (con==null) {
            con = Connector2.dbConnect(this.type);
            nisokatra=true;
        }
        list=new ArrayList<>();
        Statement state=con.createStatement();
        ResultSet resultSet = state.executeQuery("select * from "+tableName+" limit 1");
        ResultSetMetaData metadata=resultSet.getMetaData();
        int count=metadata.getColumnCount();
        if (resultSet.next()) {
            for (int i = 1; i <= count; i++) {
                System.out.println(metadata.isAutoIncrement(count)); 
                ColumnData data=new ColumnData(metadata.getColumnTypeName(i),metadata.getColumnName(i));
                list.add(data); 
            }
        }
        resultSet.close();
        if (nisokatra==true) {
            con.close();
        }
        return list;
    }

    public void generateFiles(String typeBase) throws Exception{
        Connection con = null;
        try {
            con = Connector2.dbConnect("postgresql");
            List<String> tables=this.getAllTables();
            for (String table : tables) {
                table=TableInformation.capitalizeFirstLetter(table);
                System.out.println(table);
                List<ColumnData> data=this.getColumnData(table);
                Bootstrap boot=new Bootstrap(table, this.language, data,this.pack);
                boot.generateFile();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }       
        con.close();
    }

    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    // public List<String> getAllTables(Connection con) throws Exception{
    //     List<String> list=null;
    //     boolean nisokatra=false;
    //     if (con==null) {
    //         con=new Connector2().postgresql(username, password, database);
    //         nisokatra=true;
    //     }
    //     list=new ArrayList<>();
    //     DatabaseMetaData metaData = con.getMetaData();
    //     ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});
                
    //     while (resultSet.next()) {
    //         list.add(resultSet.getString("TABLE_NAME"));
    //     }
    //     resultSet.close();
    //     if (nisokatra==true) {
    //         con.close();
    //     }
    //     return list;
    // }

    // public List<ColumnData> getColumnData(Connection con,String tableName) throws Exception{
    //     List<ColumnData> list=null;
    //     boolean nisokatra=false;
    //     if (con==null) {
    //         con=new Connector2().postgresql(username, password, database);
    //         nisokatra=true;
    //     }
    //     list=new ArrayList<>();
    //     Statement state=con.createStatement();
    //     ResultSet resultSet = state.executeQuery("select * from "+tableName+" limit 1");
    //     ResultSetMetaData metadata=resultSet.getMetaData();
    //     int count=metadata.getColumnCount();
    //     if (resultSet.next()) {
    //         for (int i = 1; i <= count; i++) {
    //             System.out.println(metadata.isAutoIncrement(count)); 
    //             ColumnData data=new ColumnData(metadata.getColumnTypeName(i),metadata.getColumnName(i));
    //             list.add(data); 
    //         }
    //     }
    //     resultSet.close();
    //     if (nisokatra==true) {
    //         con.close();
    //     }
    //     return list;
    // }

    // public void generateFiles(Connection con) throws Exception{
    //     boolean nisokatra=false;
    //     if (con==null) {
    //         con=new Connector2().postgresql(username, password, database);
    //         nisokatra=true;
    //     }
    //     List<String> tables=this.getAllTables(con);
    //     for (String table : tables) {
    //         table=TableInformation.capitalizeFirstLetter(table);
    //         System.out.println(table);
    //         List<ColumnData> data=this.getColumnData(con, table);
    //         Bootstrap boot=new Bootstrap(table, this.language, data,this.pack);
    //         boot.generateFile();
    //     }
    //     if (nisokatra==true) {
    //         con.close();
    //     }
    // }

    // public static String capitalizeFirstLetter(String str) {
    //     if (str == null || str.isEmpty()) {
    //         return str;
    //     }
    //     return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    // }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
