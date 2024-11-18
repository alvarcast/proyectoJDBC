package view;

import controller.MenuCtrl;
import model.User;

import java.sql.SQLException;

public class Menu {

    public static void startMenu() throws SQLException {
        int option;

        do{
            option = Scan.scanInt("""
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

    public static void menu(User u) throws SQLException{
        int option;

        System.out.println("Welcome, " + u.getUsername() + "!");

        do{
            option = Scan.scanInt("""
                
                === Main Menu ===
                1. Add level
                2. Complete a level
                3. Add a level to favourites
                4. Show all levels
                5. Manage levels
                6. Search level (By date, google type)
                7. Find broken entries
                8. Account settings
                9. Export DB to SQL
                10. Exit
                """
            );

            if (option < 1 || option > 10) {
                System.err.println("Invalid option, please try again");
            }
        } while (option < 1 || option > 10);

        if (option != 10){
            switch (option) {
                case 1 -> MenuCtrl.addLevel(u);
                case 2 -> MenuCtrl.beat(u);
                //case 3 -> ;
                //case 4 -> ;
                //case 5 -> ;
                //case 6 -> ;
                //case 7 -> ;
                //case 8 -> ;
                //case 9 -> ;
            }
        } else {
            System.out.println("Exiting program...");
        }
    }
}
