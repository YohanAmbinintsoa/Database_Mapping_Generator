package utilities;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import database.ColumnData;

/**
 * Bootstrap
 */
public class Bootstrap {
    String className;
    StringBuilder builder;
    String language;
    List<ColumnData> col;
    String pack;

    public Bootstrap(String className,String language,List<ColumnData> col,String pack)  throws Exception{
        this.setClassName(className);
        this.setLanguage(language);
        this.setPack(pack);
        this.col=col;
        this.builder=new StringBuilder();
    }

    

    public String getBootstrap(){
        File bootstrap=new File("./bootstrap/"+this.getLanguage()+".txt");
        builder.append("package Generated_Dao;\n");
        builder.append("public class ").append(className).append("{\n");
        for (ColumnData columnData : col) {
            this.addField(columnData.getJavaType(), columnData.getName());
        }
        builder.append("}");
        return this.builder.toString();
    }

    public void addField(String type, String name){
        builder.append(type).append(" ").append(name).append(";\n");
    }



    public void generateJavaFile() throws Exception{
        String directoryPath="Generated";
        String filePath = directoryPath + File.separator + className+".java";
        File file=new File(directoryPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileWriter writer=new FileWriter(filePath);
        writer.write(this.getBootstrap());
        writer.close();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) throws Exception{
        if (className==null||className.equals("")) {
            System.out.println("NULL");
            throw new Exception("Not a valid ClassName!");
        } 
        if (!Character.isUpperCase(className.charAt(0))) {
            System.out.println("TSY NGEZA");
            throw new Exception("Not a valid ClassName!");
        }
        if (className.contains(".")) {
            System.out.println("POINT");
            throw new Exception("Not a valid ClassName!");
        }
        this.className = className;
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(StringBuilder builder) {
        this.builder = builder;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language=language;
    }

    public List<ColumnData> getCol() {
        return col;
    }

    public void setCol(List<ColumnData> col) {
        this.col = col;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    } 
}