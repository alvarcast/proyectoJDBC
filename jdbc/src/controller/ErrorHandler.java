package controller;

import view.Menu;

import java.sql.SQLException;

public class ErrorHandler {

    public static void handle(){
        try{
            Menu.startMenu();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
