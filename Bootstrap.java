package utilities;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
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


    private static String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public String generateGetterSetterJava(List<ColumnData> colonne){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < colonne.size(); i++) {
            String type = colonne.get(i).getJavaType();
            String nom = colonne.get(i).getName();
            stringBuilder.append("\n");
            stringBuilder.append("\tpublic void set").append(capitalize(nom)).append("(")
            .append(type).append(" ").append(nom).append(") {\n ");
            stringBuilder.append("\t\tthis.").append(nom).append(" = ").append(nom).append(";\n");
            stringBuilder.append("\t}\n\n");

            stringBuilder.append("\tpublic ").append(type).append(" get").append(capitalize(nom))
            .append(" () {\n");
            stringBuilder.append("\t\treturn ").append(nom).append(";\n");
            stringBuilder.append("\t}\n");
        }
        return stringBuilder.toString();
    }

    public String getBootstrap(){
        File bootstrap=new File("./bootstrap/"+this.getLanguage()+".txt");
        int isa_ac = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(bootstrap.getAbsolutePath()));
        
            for (int i = 1; i < lines.size(); i++) 
            {
                String line = lines.get(i);
                String[] words = line.split(" ");
                String newL = "";
                for (String s : words) {
                    
                    if (s.equalsIgnoreCase("package_name")) {
                        newL = newL + " "+this.getPack() +" ";
                    }else if (s.equalsIgnoreCase("class_name")) {
                        newL = newL + " "+this.getClassName() +" ";
                    }
                    else{
                        newL = newL+"" + s + " ";
                    }
                    if (s.equals("{")) {
                        isa_ac ++;
                    }
                    else if (s.equals("}")) {
                        isa_ac --;
                    }
                    
                }
                builder.append(newL+"\n");
                System.out.println(newL);
                System.out.println("isa : "+isa_ac);
            }

            for (ColumnData columnData : col) {
                this.addField(columnData.getJavaType(), columnData.getName());
            }
            if (this.getLanguage().equalsIgnoreCase("java")) {
                builder.append(this.generateGetterSetterJava(this.col));
            }else{

            }

            for (int i = 0; i < isa_ac; i++) {
                builder.append("}\n");
            }
            // System.out.println(this.builder.toString());
            return this.builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }



    

    // public String getBootstrap(){
    //     File bootstrap=new File("./bootstrap/"+this.getLanguage()+".txt");
    //     builder.append("package Generated_Dao;\n");
    //     builder.append("public class ").append(className).append("{\n");
    //     for (ColumnData columnData : col) {
    //         this.addField(columnData.getJavaType(), columnData.getName());
    //     }
    //     builder.append("}");
    //     return this.builder.toString();
    // }

    public void addField(String type, String name){
        if (this.getLanguage().equalsIgnoreCase("c#")) {
            builder.append("\t").append(type).append(" ").append(name).append(" {get;set;}").append(";\n");
        }else{
             builder.append("\t").append(type).append(" ").append(name).append(";\n");
        }
    }



    // public void generateJavaFile() throws Exception{
    //     String directoryPath="Generated";
    //     String filePath = directoryPath + File.separator + className+".java";
    //     File file=new File(directoryPath);
    //     if (!file.exists()) {
    //         file.mkdirs();
    //     }
    //     FileWriter writer=new FileWriter(filePath);
    //     writer.write(this.getBootstrap());
    //     writer.close();
    // }



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


    public void generateFile() throws Exception{
        String directoryPath="Generated";
        String extension = this.getExtension();
        String filePath = directoryPath + File.separator + className+extension;
        
        File file=new File(directoryPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileWriter writer=new FileWriter(filePath);
        writer.write(this.getBootstrap());
        writer.close();
    }

    public String getExtension()throws Exception {
        File bootstrap=new File("./bootstrap/"+this.getLanguage()+".txt");
        try {
            List<String> lines = Files.readAllLines(Paths.get(bootstrap.getAbsolutePath()));
            for (String line : lines) 
            {
                String[] words = line.split(" ");
                if (words.length>=3 && words[2].charAt(0)!='.') {
                    throw new Exception("Extension inexistant "+ words[2]);
                }
                return words[2];
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return "";

    }
    // public void generateNetFile() throws Exception{
    //     String directoryPath="Generated";
    //     String filePath = directoryPath + File.separator + className+".cs";
    //     File file=new File(directoryPath);
    //     if (!file.exists()) {
    //         file.mkdirs();
    //     }
    //     FileWriter writer=new FileWriter(filePath);
    //     writer.write(this.getBootstrap());
    //     writer.close();
    // }
}