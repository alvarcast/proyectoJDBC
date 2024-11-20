package view;

import controller.InputValidation;
import controller.MenuCtrl;
import model.User;

import java.sql.SQLException;

public class Menu {

    public static void startMenu() throws SQLException {
        int option = 0;

        option = InputValidation.checkNumRange(1, 3, option, """
                Welcome, please select an option:
                1. Log in
                2. Sign in
                3. Exit
                """
        );

        if (option != 3){
            User u = switch (option) {
                case 1 -> MenuCtrl.login();
                case 2 -> MenuCtrl.signIn();
                default -> throw new IllegalStateException("Unexpected value: " + option);
            };

            Menu.menu(u);
        } else {
            System.out.println("Exiting program...");
        }
    }

    public static void menu(User u) throws SQLException{
        int option = 0;

        System.out.println("Welcome, " + u.getUsername() + "!");

        do{
            option = InputValidation.checkNumRange(1, 10, option, """
                
                === Main Menu ===
                1. Add level
                2. Complete a level
                3. Add a level to favourites
                4. Show data
                5. Manage levels
                6. Search level (By date, google type)
                7. Find broken entries
                8. Account settings
                9. Export DB to SQL
                10. Exit
                """
            );

            if (option != 10){
                switch (option) {
                    case 1 -> MenuCtrl.addLevel(u);
                    case 2 -> MenuCtrl.beat(u);
                    case 3 -> MenuCtrl.addFav(u);
                    case 4 -> Menu.dataMenu(u);
                    //case 5 -> ;
                    //case 6 -> ;
                    //case 7 -> ;
                    //case 8 -> ;
                    //case 9 -> ;
                }
            } else {
                System.out.println("Exiting program...");
            }
        } while (option != 10);
    }

    public static void dataMenu(User u) throws SQLException{
        int option = 0;

        option = InputValidation.checkNumRange(1, 4, option, """
                
                Select an option:
                1. View levels
                2. View favourite levels
                3. View data base
                4. Back
                """
        );

        switch (option) {
            case 1 -> MenuCtrl.viewLevels(u, true);
            case 2 -> MenuCtrl.viewFavourites(u);
            case 3 -> MenuCtrl.viewDataBase();
        };
    }
}
