package controller;

import model.DataBase;
import view.Scan;

import java.util.ArrayList;

public class DbController {

    public static void select(){
        String pattern = "(?i)\\s*SELECT\\s+.*\\s+FROM\\s+\\w+(\\s+WHERE\\s+.*)?\\s*;?\\s*";
        String consult;
        boolean cond;

        do{
            consult = Scan.scanText("Introduzca la sentencia SQL (Select):");

            cond = consult.matches(pattern);

            if (!cond){
                System.err.println("La sentencia introducida no es un SELECT, por favor, inténtelo de nuevo");
            }
        } while (!cond);

        connect(consult, true);
    }

    public static void create(String tablename, ArrayList<String> data){
        String consult = "CREATE TABLE " + tablename;
    }

    public static void insert(String tablename){

    }

    public static void update(String tablename, String clause, String condition){

    }

    public static void delete(String tablename, String clause, String condition){
        String consult = "DELETE " + clause + " FROM " + tablename + " WHERE " + condition;
    }

    public static void truncate(){

    }

    private static void connect(String consult, boolean view){
        DataBase db = new DataBase("user","1234","farmacos");
        db.consult(consult, view);
    }
}
