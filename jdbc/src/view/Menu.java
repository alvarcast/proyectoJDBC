package view;

import controller.DbManager;
import controller.InputValidation;
import controller.MenuCtrl;
import model.User;

import java.sql.SQLException;

public class Menu {

    //Menu que sale al principio, para iniciar sesion o crear una cuenta
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

    //Menu de opciones
    public static void menu(User u) throws SQLException{
        int option = 0;

        System.out.println("Welcome, " + u.getUsername() + "!");

        do{

            if (u.getUid() != 0){
                option = InputValidation.checkNumRange(1, 11, option, """
                
                === Main Menu ===
                1. Add level
                2. Complete a level
                3. Add a level to favourites
                4. Show data
                5. Manage levels
                6. Search
                7. Find broken (null) entries
                8. Account settings
                9. Export DB to SQL
                10. Ban reasons
                11. Exit
                """
                );
            } else {
                option = 11;
            }

            if (option != 11){
                switch (option) {
                    case 1 -> MenuCtrl.addLevel(u);
                    case 2 -> MenuCtrl.beat(u);
                    case 3 -> MenuCtrl.addFav(u);
                    case 4 -> Menu.dataMenu(u);
                    case 5 -> Menu.manageLevelsMenu(u);
                    case 6 -> Menu.searchMenu(u);
                    case 7 -> MenuCtrl.findNull();
                    case 8 -> Menu.manageUserMenu(u);
                    case 9 -> DbManager.exportDB();
                    case 10 -> Menu.banReasons();
                }
            } else {
                System.out.println("Exiting program...");
            }
        } while (option != 11);
    }

    //Menu para ver x tipo de niveles
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
            case 1 -> MenuCtrl.viewLevels(u);
            case 2 -> MenuCtrl.viewFavourites(u);
            case 3 -> MenuCtrl.viewDataBase();
        }
    }

    //Menu de opciones de niveles
    public static void manageLevelsMenu(User u) throws SQLException{
        int option = 0;

        option = InputValidation.checkNumRange(1, 4, option, """
                
                Select an option:
                1. Manage levels
                2. Manage beaten levels
                3. Manage favourite levels
                4. Back
                """
        );

        switch (option) {
            case 1 -> Menu.manageLevel(u);
            case 2 -> Menu.manageBeaten(u);
            case 3 -> Menu.manageFavourites(u);
        }
    }

    //Menu de opciones de la tabla level
    public static void manageLevel(User u) throws SQLException{
        int option = 0;

        option = InputValidation.checkNumRange(1, 3, option, """
                
                Select an option:
                1. Update a level
                2. Delete a level
                3. Back
                """
        );

        switch (option) {
            case 1 -> MenuCtrl.manageLevelUpdate(u);
            case 2 -> MenuCtrl.deleteLevel(u);
        }
    }

    //Menu de opciones de la tabla beaten_level
    public static void manageBeaten(User u) throws SQLException{
        int option = 0;

        option = InputValidation.checkNumRange(1, 3, option, """
                
                Select an option:
                1. Update a beaten level
                2. Un-beat a level
                3. Back
                """
        );

        switch (option) {
            case 1 -> MenuCtrl.manageBeatenLevelUpdate(u);
            case 2 -> MenuCtrl.unBeat(u);
        }
    }

    //Menu de opciones de la tabla favourites
    public static void manageFavourites(User u) throws SQLException{
        int option = 0;

        option = InputValidation.checkNumRange(1, 2, option, """
                
                Select an option:
                1. Un-favourite a level
                2. Back
                """
        );

        if (option == 1) {
            MenuCtrl.unFavourite(u);
        }
    }

    //Menu de opciones de usuario e informacion personal
    public static void manageUserMenu(User u) throws SQLException{
        int option = 0;

        option = InputValidation.checkNumRange(1, 3, option, """
                
                Select an option:
                1. Manage user information
                2. Manage personal information
                3. Back
                """
        );

        switch (option) {
            case 1 -> Menu.manageUser(u);
            case 2 -> MenuCtrl.managePersonalInformationUpdate(u);
        }
    }

    //Menu de opciones de la tabla user
    public static void manageUser(User u) throws SQLException{
        int option = 0;

        option = InputValidation.checkNumRange(1, 3, option, """
                
                Select an option:
                1. Update user information
                2. Delete account
                3. Back
                """
        );

        switch (option) {
            case 1 -> MenuCtrl.manageUserUpdate(u);
            case 2 -> MenuCtrl.deleteUser(u);
        }
    }

    //Menu para elegir el tipo de busqueda
    public static void searchMenu(User u) throws SQLException{
        int option = 0;

        option = InputValidation.checkNumRange(1, 3, option, """
                
                Select an option:
                1. Google type search (All DB)
                2. Date search
                3. Back
                """
        );

        switch (option) {
            case 1 -> MenuCtrl.googleSearch();
            case 2 -> MenuCtrl.dateSearch(u);
        }
    }

    //Imprime las normas y razones de baneo, y cosas que se deberia evitar poner
    public static void banReasons(){
        System.out.println("""
                
                === Ban reasons ===
                1. Trying to do a sqlinject will get you automatically ban, so avoid using the following when asked por an input:
                - or
                - and
                - is
                - like
                - join
                - =
                - +
                - '
                2. Don't worry about levels with those words, inputing that won't get you banned.
                """)
        ;
        Scan.waitForInput();
    }
}
