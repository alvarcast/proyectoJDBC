package controller;

import view.Menu;

import java.sql.SQLException;

public class ErrorHandler {

    //Esto captura todos los errores sql del programa, que son bastantes
    public static void handle(){
        try{
            Menu.startMenu();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
