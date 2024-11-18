package controller;

import model.*;
import view.Scan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

            rs = DbManager.runSQL("SELECT id FROM user WHERE username = '" + username + "';", false, true);
            if (!rs.next()){
                System.err.println("That username does not exist, try again");
            } else {
                out = true;
            }
        } while (!out);

        out = false;

        do{
            password = Scan.scanText("Password:");
            password = Encrypt.crypt(password);

            rs = DbManager.runSQL("SELECT password FROM user WHERE username = '" + username + "';", false, true);
            rs.next();
            checkPassword = (String) rs.getObject(1);
            if (!password.equals(checkPassword)){
                System.err.println("The password is incorrect, try again");
            } else {
                out = true;
            }
        } while (!out);

        rs = DbManager.runSQL("SELECT id FROM user WHERE username = '" + username + "';", false, true);
        rs.next();
        uid = ((Number) rs.getObject(1)).intValue();

        System.out.println("Logged in correctly as " + username);

        return new User(uid, username, password);
    }

    public static User signIn() throws SQLException{
        ResultSet rs;
        int uid;
        String query;
        String name;
        String surname;
        String email;
        String username;
        String password;
        String repeat;

        name = Scan.scanText("Real name:");
        surname = Scan.scanText("Surname:");

        do{
            email = Scan.scanText("E-Mail:");
        } while (DbManager.checkDupe("personal_info", "uid", "email", email));

        do{
            username = Scan.scanText("Username:");
        } while (DbManager.checkDupe("user", "id", "username", username));

        do{
            password = Scan.scanText("Password:");
            repeat = Scan.scanText("Repeat password:");
            if (!password.equals(repeat)){
                System.err.println("The passwords do not match, try again");
            }
        } while (!password.equals(repeat));

        password = Encrypt.crypt(password);

        query = "INSERT INTO user (username, password) VALUES ('" + username + "','" + password + "');";
        DbManager.runSQL(query, false, false);

        rs = DbManager.runSQL("SELECT id FROM user WHERE username = '" + username + "';", false, true);
        rs.next();
        uid = ((Number) rs.getObject(1)).intValue();

        query = "INSERT INTO personal_info (uid, name, surname, email) VALUES ('" + uid + "','" + name + "','" + surname + "','" + email + "');";
        DbManager.runSQL(query, false, false);

        System.out.println("New user '" + username + "' was created");

        return new User(uid, username, password);
    }

    public static void addLevel(User u){
        //LevelList levelList = new LevelList();
        String query;

        String game_id;
        String level_name;
        String creator;
        String music;
        int difficulty;
        float diff_num;
        int attempts;
        int beaten = 0;
        String start_date;

        game_id = Scan.scanText("Introduce the game ID:");
        level_name = Scan.scanText("Level name:");
        creator = Scan.scanText("Creator:");
        music = Scan.scanText("Song:");

        do{
            difficulty = Scan.scanInt("""
                    
                    Choose the difficulty:
                    1. Easy Demon
                    2. Medium Demon
                    3. Hard Demon
                    4. Insane Demon
                    5. Extreme Demon
                    """
            );

            if (difficulty < 1 || difficulty > 5){
                System.err.println("Invalid option, please try again");
            }
        } while (difficulty < 1 || difficulty > 5);

        diff_num = Scan.scanFloat("Introduce the numerical difficulty (GDDL):");
        attempts = Scan.scanInt("Introduce the current number of attempts:");

        start_date = askDate();

        //Level l = new Level(u.getUid(), game_id, level_name, creator, music, difficulty, diff_num, attempts, beaten, start_date);
        //levelList.add(level);

        query = "INSERT INTO level (uid, game_id, level_name, creator, music, difficulty, diff_num, attempts, beaten, start_date) " +
                "VALUES ('" + u.getUid() + "','" +
                        game_id + "','" +
                        level_name + "','" +
                        creator + "','" +
                        music + "','" +
                        difficulty + "','" +
                        diff_num + "','" +
                        attempts + "','" +
                        beaten + "','" +
                        start_date +
                "');"
        ;

        DbManager.runSQL(query, false, false);

        System.out.println("The level " + level_name + " was added correctly");

        //return levelList;
    }

    public static void beat(User u) throws SQLException{
        //BeatenLevelList beatenLevelList = new BeatenLevelList();
        int lid;
        String query;

        float music_rate = 0;
        float gameplay_rate = 0;
        float deco_rate = 0;
        float fx_rate = 0;
        float enjoyment;
        int total_attempts;
        String end_date;

        lid = DbManager.getLevelId(u);

        music_rate = InputValidation.checkNumRange(0, 10, music_rate, "Music rating (0-10):");

        gameplay_rate = InputValidation.checkNumRange(0, 10, gameplay_rate, "Gameplay rating (0-10):");

        deco_rate = InputValidation.checkNumRange(0, 10, deco_rate, "Decoration rating (0-10):");

        fx_rate = InputValidation.checkNumRange(0, 10, fx_rate, "Effects rating (0-10):");

        enjoyment = (music_rate + gameplay_rate + deco_rate + fx_rate) / 4;

        total_attempts = Scan.scanInt("Total attempts:");

        end_date = askDate();

        //BeatenLevel beatenLevel = new BeatenLevel(lid, music_rate, gameplay_rate, deco_rate, fx_rate, enjoyment, total_attempts, end_date);
        //beatenLevelList.add(beatenLevel);

        query = "INSERT INTO beaten_level (lid, music_rate, gameplay_rate, deco_rate, fx_rate, enjoyment, total_attempts, end_date) " +
                "VALUES ('" + lid + "','" +
                music_rate + "','" +
                gameplay_rate + "','" +
                deco_rate + "','" +
                fx_rate + "','" +
                enjoyment + "','" +
                total_attempts + "','" +
                end_date +
                "');"
        ;

        DbManager.runSQL(query, false, false);

        System.out.println("The level was added to the completion list correctly");

        //return beatenLevelList;
    }

    public static void addFav(User u) throws SQLException{
        int lid;
        String query;

        lid = DbManager.getLevelId(u);

        //Check this query
        query = "INSERT INTO favourite_demons (uid, lid) VALUES ('" + u.getUid() + "','" + lid + "');";
        DbManager.runSQL(query, false, false);

        System.out.println("The level was added correctly to your favourites");
    }

    private static String askDate(){
        int option;
        String date;

        do{
            option = Scan.scanInt("""
                    
                    Do you wish to set a custom end date (default is today)?
                    1. Yes
                    2. No
                    """
            );

            if (option < 1 || option > 2){
                System.err.println("Invalid option, please try again");
            }
        } while (option < 1 || option > 2);

        if (option == 1){
            date = Scan.scanText("Introduce date (XX-XX-XXXX):");
        } else {
            date = LocalDate.now().toString();
        }

        return date;
    }
}
