package controller;

import model.Connex;
import model.SingletonDB;

import java.net.ConnectException;

public class SuperCtrl {

    public static void runProgram(){
        startProgram();
        ErrorHandler.handle();
        endProgram();
    }

    private static void startProgram(){
        try{
            SingletonDB db_instance = SingletonDB.getInstance();
            Connex.getInstance(db_instance.getUsername(), db_instance.getPassword(), db_instance.getDatabase(), db_instance.getServername());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void endProgram(){
        Connex.close();
    }
}
