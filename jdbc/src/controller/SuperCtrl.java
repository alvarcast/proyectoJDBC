package controller;

import model.Connex;
import model.SingletonDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SuperCtrl {

    public static void runProgram(){
        startProgram();
        endProgram();
    }

    private static void startProgram(){
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> lines = new ArrayList<String>();

        String externalIP = "";
        String cadena;
        boolean banned = false;

        try {
            externalIP = ExternalIpFetcher.getExternalIP();
            System.out.println(externalIP);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fr = new FileReader("data/blacklist.txt");
            br = new BufferedReader(fr);

            do {
                cadena = br.readLine();
                if(cadena != null){
                    if(!cadena.isEmpty() && !cadena.equals(" ")){
                        if (cadena.equals(externalIP)){
                            banned = true;
                        }
                    }
                }
            } while (cadena != null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null){
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        if (!banned){
            try{
                SingletonDB db_instance = SingletonDB.getInstance();
                Connex.getInstance(db_instance.getUsername(), db_instance.getPassword(), db_instance.getDatabase(), db_instance.getServername());
            } catch (Exception e){
                e.printStackTrace();
            }

            ErrorHandler.handle();
        } else {
            System.err.println("You are banned >:(");
        }
    }

    private static void endProgram(){
        Connex.close();
    }
}
