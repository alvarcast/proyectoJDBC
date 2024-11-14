package controller;

import model.User;
import view.Scan;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuCtrl {

    public static User login() throws SQLException{
        int uid;
        String username;
        String password;
        String checkPassword;
        boolean out = false;
        ResultSet rs;

        do{
            username = Scan.scanText("Username:");

            rs = DbController.runSQL("SELECT id FROM user WHERE username = '" + username + "';", false, true);
            if (!rs.next()){
                System.err.println("That username does not exist, try again");
            } else {
                out = true;
            }
        } while (!out);

        out = false;

        do{
            password = Scan.scanText("Password:");

            rs = DbController.runSQL("SELECT password FROM user WHERE username = '" + username + "';", false, true);
            rs.next();
            checkPassword = (String) rs.getObject(1);
            if (!password.equals(checkPassword)){
                System.err.println("The password is incorrect, try again");
            } else {
                out = true;
            }
        } while (!out);

        rs = DbController.runSQL("SELECT id FROM user WHERE username = '" + username + "';", false, true);
        rs.next();
        uid = ((Number) rs.getObject(1)).intValue();

        System.out.println("Logged in correctly as " + username);

        return new User(uid, username, password);
    }

    public static User signIn() throws SQLException{
        ResultSet rs;
        int uid = 0;
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

        rec_email = Scan.scanText("Recuperation email:");

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

        String query = "INSERT INTO user (username, password) VALUES ('" + username + "','" + password + "');";
        DbController.runSQL(query, false, false);

        rs = DbController.runSQL("SELECT id FROM user WHERE username = '" + username + "';", false, true);
        rs.next();
        uid = ((Number) rs.getObject(1)).intValue();

        String second_query = "INSERT INTO personal_info (uid, name, surname, email, rec_email) VALUES ('" + uid + "','" + name + "','" + surname + "','" + email + "','" + rec_email + "');";
        DbController.runSQL(second_query, false, false);

        System.out.println("New user '" + username + "' was created");
        return new User(uid, username, password);
    }
}
