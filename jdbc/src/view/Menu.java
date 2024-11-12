package view;

import controller.MenuCtrl;

public class Menu {

    public static void startMenu(){

        int option = 0;

        do{
            option = Scan.scanNum("""
                Welcome, please select an option:
                1. Log in
                2. Sign in
                """
            );
            if (option < 1 || option > 2) {
                System.err.println("Invalid option, please try again");
            }
        } while (option < 1 || option > 2);

        switch (option) {
            case 1 -> MenuCtrl.login();
            case 2 -> MenuCtrl.signIn();
        }
    }

    public static void menu(){

    }
}
