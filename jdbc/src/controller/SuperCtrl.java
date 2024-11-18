package controller;

import model.Connex;

public class SuperCtrl {

    public static void runProgram(){
        ErrorHandler.handle();
        endProgram();
    }

    private static void endProgram(){
        Connex.close();
    }
}
