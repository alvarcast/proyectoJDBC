package controller;

import model.*;
import view.Printer;
import view.Scan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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

            rs = DbManager.runSQL("SELECT uid FROM user WHERE username = '" + username + "';", true);
            if (rs.next()){
                out = true;
            } else {
                System.err.println("That user does not exist, try again");
            }
        } while (!out);

        out = false;

        do{
            password = Scan.scanText("Password:");
            password = Encrypt.crypt(password);

            rs = DbManager.runSQL("SELECT password FROM user WHERE username = '" + username + "';", true);
            rs.next();
            checkPassword = (String) rs.getObject(1);
            if (!password.equals(checkPassword)){
                System.err.println("The password is incorrect, try again");
            } else {
                out = true;
            }
        } while (!out);

        rs = DbManager.runSQL("SELECT uid FROM user WHERE username = '" + username + "';", true);
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
        } while (DbManager.checkDupe("user", "uid", "username", username));

        do{
            password = Scan.scanText("Password:");
            repeat = Scan.scanText("Repeat password:");
            if (!password.equals(repeat)){
                System.err.println("The passwords do not match, try again");
            }
        } while (!password.equals(repeat));

        password = Encrypt.crypt(password);

        query = "INSERT INTO user (username, password) VALUES ('" + username + "','" + password + "');";
        DbManager.runSQL(query, false);

        rs = DbManager.runSQL("SELECT uid FROM user WHERE username = '" + username + "';", true);
        rs.next();
        uid = ((Number) rs.getObject(1)).intValue();

        query = "INSERT INTO personal_info (uid, name, surname, email) VALUES ('" + uid + "','" + name + "','" + surname + "','" + email + "');";
        DbManager.runSQL(query, false);

        System.out.println("New user '" + username + "' was created");

        return new User(uid, username, password);
    }

    public static void addLevel(User u) throws SQLException{
        String query;

        int game_id;
        String level_name;
        String creator;
        String music;
        int difficulty;
        float diff_num = 0;
        int attempts;
        int beaten = 0;
        String start_date;

        game_id = DbManager.checkGameId(u);

        level_name = DbManager.checkLevelName(u);

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

        diff_num = InputValidation.checkNumRange(1, 35, diff_num, "Introduce the numerical difficulty (GDDL):");
        attempts = Scan.scanInt("Introduce the current number of attempts:");

        start_date = askDate();

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

        DbManager.runSQL(query, false);

        System.out.println("The level " + level_name + " was added correctly to your level list");
    }

    public static void beat(User u) throws SQLException{
        String query;
        int lid;

        float music_rate = 0;
        float gameplay_rate = 0;
        float deco_rate = 0;
        float fx_rate = 0;
        float enjoyment;
        int total_attempts;
        String end_date;

        lid = DbManager.checkIfBeaten(u);

        music_rate = InputValidation.checkNumRange(0, 10, music_rate, "Music rating (0-10):");

        gameplay_rate = InputValidation.checkNumRange(0, 10, gameplay_rate, "Gameplay rating (0-10):");

        deco_rate = InputValidation.checkNumRange(0, 10, deco_rate, "Decoration rating (0-10):");

        fx_rate = InputValidation.checkNumRange(0, 10, fx_rate, "Effects rating (0-10):");

        enjoyment = (music_rate + gameplay_rate + deco_rate + fx_rate) / 4;

        total_attempts = Scan.scanInt("Total attempts:");

        end_date = askDate();

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

        DbManager.runSQL(query, false);

        query = "UPDATE level SET beaten = 1 WHERE lid = '" + lid + "';";

        DbManager.runSQL(query, false);

        System.out.println("The level was added to the completion list correctly");
    }

    public static void addFav(User u) throws SQLException{
        int lid;
        String query;

        lid = DbManager.checkFavourite(u);

        query = "INSERT INTO fav_demons (uid, lid) VALUES ('" + u.getUid() + "','" + lid + "');";
        DbManager.runSQL(query, false);

        System.out.println("The level was added correctly to your favourites");
    }

    private static String askDate(){
        int option;
        String date = "";

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
            date = InputValidation.checkDate(date);
        } else {
            date = LocalDate.now().toString();
        }

        return date;
    }

    public static void viewLevels(User u, boolean justLevels) throws SQLException{
        LevelList levelList = new LevelList();
        BeatenLevelList beatenLevelList = new BeatenLevelList();
        ResultSet rs;
        ResultSet beaten_rs;
        String query;

        int lid;
        int game_id;
        String level_name;
        String creator;
        String music;
        String difficulty;
        float diff_num;
        int attempts;
        int beaten;
        String start_date;

        float music_rate;
        float gameplay_rate;
        float deco_rate;
        float fx_rate;
        float enjoyment;
        int total_attempts;
        String end_date;

        query = "SELECT game_id, level_name, creator, music, difficulty_name, diff_num, attempts, beaten, start_date " +
                "FROM level l " +
                "INNER JOIN demon_difficulties d ON l.difficulty = d.did " +
                "WHERE uid = '" + u.getUid() + "';"
        ;
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            game_id = rs.getInt("game_id");
            level_name = rs.getString("level_name");
            creator = rs.getString("creator");
            music = rs.getString("music");
            difficulty = rs.getString("difficulty_name");
            diff_num = rs.getFloat("diff_num");
            attempts = rs.getInt("attempts");
            beaten = rs.getInt("beaten");
            start_date = rs.getString("start_date");

            lid = DbManager.getLevelId(u, level_name);

            levelList.add(new Level(lid, game_id, level_name, creator, music, difficulty, diff_num, attempts, beaten, start_date));

            if (beaten == 1){
                lid = DbManager.getLevelId(u, level_name);

                query = "SELECT music_rate, gameplay_rate, deco_rate, fx_rate, enjoyment, total_attempts, end_date " +
                        "FROM beaten_level WHERE lid = '" + lid + "';"
                ;
                beaten_rs = DbManager.runSQL(query, true);

                beaten_rs.next();
                music_rate = beaten_rs.getFloat("music_rate");
                gameplay_rate = beaten_rs.getFloat("gameplay_rate");
                deco_rate = beaten_rs.getFloat("deco_rate");
                fx_rate = beaten_rs.getFloat("fx_rate");
                enjoyment = beaten_rs.getFloat("enjoyment");
                total_attempts = beaten_rs.getInt("total_attempts");
                end_date = beaten_rs.getString("end_date");

                beatenLevelList.add(new BeatenLevel(lid, music_rate, gameplay_rate, deco_rate, fx_rate, enjoyment, total_attempts, end_date));
            }
        }

        Printer.printLevels(levelList, beatenLevelList);
    }

    public static void viewFavourites(User u) throws SQLException{
        LevelList levelList = new LevelList();
        ArrayList<Integer> lidsList = new ArrayList<Integer>();
        ResultSet rs;
        String query;

        int game_id;
        String level_name;
        String creator;
        String music;
        String difficulty;

        query = "SELECT lid FROM fav_demons WHERE uid = '" + u.getUid() + "';";
        rs = DbManager.runSQL(query, true);

        while (rs.next()) {
            lidsList.add(rs.getInt("lid"));
        }

        for (Integer integer : lidsList) {
            query = "SELECT game_id, level_name, creator, music, difficulty_name " +
                    "FROM level l " +
                    "INNER JOIN demon_difficulties d ON l.difficulty = d.did " +
                    "WHERE uid = '" + u.getUid() + "' AND lid = '" + integer + "';"
            ;
            rs = DbManager.runSQL(query, true);
            rs.next();

            game_id = rs.getInt("game_id");
            level_name = rs.getString("level_name");
            creator = rs.getString("creator");
            music = rs.getString("music");
            difficulty = rs.getString("difficulty_name");

            levelList.add(new Level(game_id, level_name, creator, music, difficulty));
        }

        Printer.printFavourites(levelList);
    }

    public static void viewDataBase() throws SQLException{
        UserList ul = new UserList();
        PersonalInfoList pil = new PersonalInfoList();
        LevelList ll = new LevelList();
        FavouriteLevelList fll = new FavouriteLevelList();
        DifficultyList dl = new DifficultyList();
        BeatenLevelList bll = new BeatenLevelList();

        ResultSet lrs;
        ResultSet rs;
        String query;

        //User
        int uid;
        String username;
        String password;

        //Personal info
        String name;
        String surname;
        String email;

        //Levels
        int lid;
        int game_id;
        String level_name;
        String creator;
        String music;
        String difficulty;
        float diff_num;
        int attempts;
        int beaten;
        String start_date;

        //Beaten levels
        float music_rate;
        float gameplay_rate;
        float deco_rate;
        float fx_rate;
        float enjoyment;
        int total_attempts;
        String end_date;

        //Favourite levels (uid and lid)

        //Difficulties
        int did;
        String difficulty_name;

        query = "SELECT * FROM user";
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            uid = rs.getInt("uid");
            username = rs.getString("username");
            password = rs.getString("password");

            ul.add(new User(uid, username, password));
        }

        query = "SELECT username, name, surname, email " +
                "FROM personal_info p " +
                "INNER JOIN user u ON p.uid = u.uid;"
        ;
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            username = rs.getString("username");
            name = rs.getString("name");
            surname = rs.getString("surname");
            email = rs.getString("email");

            pil.add(new PersonalInfo(username, name, surname, email));
        }

        query = "SELECT lid, username, game_id, level_name, creator, music, difficulty_name, diff_num, attempts, beaten, start_date " +
                "FROM level l " +
                "INNER JOIN user u ON l.uid = u.uid " +
                "INNER JOIN demon_difficulties d ON l.difficulty = d.did;"
        ;
        lrs = DbManager.runSQL(query, true);

        while (lrs.next()){
            lid = lrs.getInt("lid");
            username = lrs.getString("username");
            game_id = lrs.getInt("game_id");
            level_name = lrs.getString("level_name");
            creator = lrs.getString("creator");
            music = lrs.getString("music");
            difficulty = lrs.getString("difficulty_name");
            diff_num = lrs.getFloat("diff_num");
            attempts = lrs.getInt("attempts");
            beaten = lrs.getInt("beaten");
            start_date = lrs.getString("start_date");

            ll.add(new Level(lid, username, game_id, level_name, creator, music, difficulty, diff_num, attempts, beaten, start_date));

            if (beaten == 1){
                query = "SELECT * " +
                        "FROM beaten_Level WHERE lid = '" + lid + "';"
                ;
                rs = DbManager.runSQL(query, true);

                while (rs.next()){
                    lid = rs.getInt("lid");
                    music_rate = rs.getFloat("music_rate");
                    gameplay_rate = rs.getFloat("gameplay_rate");
                    deco_rate = rs.getFloat("deco_rate");
                    fx_rate = rs.getFloat("fx_rate");
                    enjoyment = rs.getFloat("enjoyment");
                    total_attempts = rs.getInt("total_attempts");
                    end_date = rs.getString("end_date");

                    bll.add(new BeatenLevel(lid, music_rate, gameplay_rate, deco_rate, fx_rate, enjoyment, total_attempts, end_date));
                }
            }
        }

        query = "SELECT username, level_name " +
                "FROM fav_demons f " +
                "INNER JOIN user u ON f.uid = u.uid " +
                "INNER JOIN level l ON f.lid = l.lid;"
        ;
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            username = rs.getString("username");
            level_name = rs.getString("level_name");

            fll.add(new FavouriteLevel(username, level_name));
        }

        query = "SELECT * FROM demon_difficulties;";
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            did = rs.getInt("did");
            difficulty_name = rs.getString("difficulty_name");

            dl.add(new Difficulty(did, difficulty_name));
        }

        Printer.printAll(new MegaClass(ul, pil, ll, bll, fll, dl));
    }
}
