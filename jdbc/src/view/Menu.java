package view;

import controller.ErrorHandler;
import controller.MenuCtrl;
import model.User;

import java.sql.SQLException;

public class Menu {

    public static void startMenu() throws SQLException {

        int option;

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

        User u = switch (option) {
            case 1 -> MenuCtrl.login();
            case 2 -> MenuCtrl.signIn();
            default -> throw new IllegalStateException("Unexpected value: " + option);
        };

        Menu.menu(u);
    }

    public static void menu(User u){
        System.out.println("Welcome, " + u.getUsername() + "!");
    }
}
