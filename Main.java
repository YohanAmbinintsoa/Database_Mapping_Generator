package Main;

import java.util.List;

import database.TableInformation;
import utilities.Bootstrap;

public class Main {
    public static void main(String[] args) {
        try {
           TableInformation info=new TableInformation("fiara", "java","fiara");
           info.generateFiles(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
