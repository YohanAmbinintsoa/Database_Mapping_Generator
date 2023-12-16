package Main;

import java.lang.reflect.Method;
import java.util.List;

import database.Connector2;
import database.TableInformation;
import utilities.Bootstrap;

public class Main {
    public static void main(String[] args) {
        try {
            String nom_base = args[0];
            String typeBase = "postgresql";
            String language = "java";
            TableInformation info=new TableInformation(typeBase,nom_base, language,nom_base);
        //    TableInformation info=new TableInformation("prog_vary", "c#","fiara");
            info.generateFiles(typeBase);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
