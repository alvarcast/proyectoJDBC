package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Banner {

    //Metodo para añadir la ip pública del usuario a la blacklist y cierra el programa
    public static void banUser(){
        FileWriter fw = null;
        PrintWriter pw = null;
        String ip = "";

        try {
            ip = IpManager.getExternalIP();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            //Escribo en el fichero blacklist.txt
            fw = new FileWriter("data/blacklist.txt", true);
            pw = new PrintWriter(fw);

            pw.println(ip);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                if (fw != null){
                    fw.close();
                }
                if (pw != null){
                    pw.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        //Uso autorizado de System.exit(0) el dia 22/11/2024 alrededor de las 10:15
        System.exit(0);
    }
}
