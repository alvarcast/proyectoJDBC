package controller;

import model.Connex;
import model.SingletonDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SuperCtrl {

    //Inicio y fin de programa. Unica cose que llama el main
    public static void runProgram(){
        startProgram();
        endProgram();
    }

    //Primero comprueba si la IP está en la blacklist, y si esta, acaba este metodo y empieza endProgram()
    private static void startProgram(){
        FileReader fr = null;
        BufferedReader br = null;

        String externalIP = "";
        String cadena;
        boolean banned = false;

        try {
            externalIP = IpManager.getExternalIP();
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

        //Si no esta en la blacklist, seguir con el programa
        if (!banned){
            try{
                SingletonDB db_instance = SingletonDB.getInstance();
                Connex.getInstance(db_instance.getUsername(), db_instance.getPassword(), db_instance.getDatabase(), db_instance.getServername());
            } catch (Exception e){
                e.printStackTrace();
            }

            ErrorHandler.handle();
        } else {
            System.err.println("You are banned >:( Get out of my program or I will dox you!");
        }
    }

    //Metodo de fin de programa. Cierra la conexion
    private static void endProgram(){
        Connex.close();
    }
}
