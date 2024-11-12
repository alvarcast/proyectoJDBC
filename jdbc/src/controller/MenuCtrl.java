package controller;

import view.Scan;

public class MenuCtrl {

    public static void login(){
        String username;
        String password;
    }

    public static void signIn(){
        String name;
        String surname;
        String email;
        String rec_email;
        String username;
        String password;
        String repeat;

        name = Scan.scanText("Real name:");
        surname = Scan.scanText("Surname:");

        do{
            email = Scan.scanText("E-Mail:");
        } while (DbController.checkDupe("personal_info", "uid", "email", email));

        rec_email = Scan.scanText("Surname:");

        do{
            username = Scan.scanText("Username:");
        } while (DbController.checkDupe("user", "id", "username", username));

        do{
            password = Scan.scanText("Password:");
            repeat = Scan.scanText("Repeat password:");
            if (!password.equals(repeat)){
                System.err.println("The passwords do not match, try again");
            }
        } while (!password.equals(repeat));
    }
}
