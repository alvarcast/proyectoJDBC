package controller;

import model.*;
import view.Printer;
import view.Scan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MenuCtrl {

    //Metodo para hacer login como usuario en local y en la BD
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

    //Metodo para hacer un nuevo usuario en local y en la BD
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

        do {
            name = Scan.scanText("Real name: ");
        } while (InputValidation.countChar(50, name));

        do {
            surname = Scan.scanText("Surname: ");
        } while (InputValidation.countChar(50, name));

        do{
            email = Scan.scanText("E-Mail: ");
        } while (DbManager.checkDupe("personal_info", "uid", "email", email) && InputValidation.countChar(100, email));

        do{
            username = Scan.scanText("Username: ");
        } while (DbManager.checkDupe("user", "uid", "username", username) && InputValidation.countChar(50, username));

        do{
            do {
                password = Scan.scanText("Password: ");
            } while (InputValidation.countChar(30, password));
            repeat = Scan.scanText("Repeat password: ");
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

    //Metodo para hacer añadir un nivel a la BD
    public static void addLevel(User u) throws SQLException{
        String query;

        int game_id;
        String level_name;
        String creator;
        String music;
        int difficulty = 0;
        float diff_num = 0;
        int attempts;
        int beaten = 0;
        String start_date;

        do {
            game_id = DbManager.checkGameId(u);
        } while (InputValidation.countChar(11, game_id));

        do {
            level_name = DbManager.checkLevelName(u);
        } while (InputValidation.countChar(20, level_name));

        do {
            creator = Scan.scanText("Creator:");
        } while (InputValidation.countChar(20, creator));

        do {
            music = Scan.scanText("Song:");
        } while (InputValidation.countChar(100, music));

        difficulty = InputValidation.checkNumRange(1, 5, difficulty, """
                    
                    Choose the difficulty:
                    1. Easy Demon
                    2. Medium Demon
                    3. Hard Demon
                    4. Insane Demon
                    5. Extreme Demon
                    """)
        ;

        diff_num = InputValidation.checkNumRange(1, 35, diff_num, "Introduce the numerical difficulty (GDDL):");

        do {
            attempts = Scan.scanInt("Introduce the current number of attempts:");
        } while (InputValidation.countChar(11, attempts));

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

    //Metodo para marcar un nivel como completado y añadir su informacion post-completado a la tabla beaten_levels
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

        if (lid != -1){
            music_rate = InputValidation.checkNumRange(0, 10, music_rate, "Music rating (0-10):");

            gameplay_rate = InputValidation.checkNumRange(0, 10, gameplay_rate, "Gameplay rating (0-10):");

            deco_rate = InputValidation.checkNumRange(0, 10, deco_rate, "Decoration rating (0-10):");

            fx_rate = InputValidation.checkNumRange(0, 10, fx_rate, "Effects rating (0-10):");

            enjoyment = (music_rate + gameplay_rate + deco_rate + fx_rate) / 4;

            do {
                total_attempts = Scan.scanInt("Total attempts:");
            } while (InputValidation.countChar(11, total_attempts));

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
    }

    //Metodo para añadir niveles a favoritos en la tabla fav_demons
    public static void addFav(User u) throws SQLException{
        int lid;
        String query;

        lid = DbManager.checkFavourite(u);

        query = "INSERT INTO fav_demons (uid, lid) VALUES ('" + u.getUid() + "','" + lid + "');";
        DbManager.runSQL(query, false);
    }

    //Metodo para preguntar fecha. Si se pone una fecha personalizada, llama a InputValidation.checkDate()
    private static String askDate(){
        int option;
        String date;

        do{
            option = Scan.scanInt("""
                    
                    Do you wish to set a custom date (default is today)?
                    1. Yes
                    2. No
                    """
            );

            if (option < 1 || option > 2){
                System.err.println("Invalid option, please try again");
            }
        } while (option < 1 || option > 2);

        if (option == 1){
            date = InputValidation.checkDate();
        } else {
            date = LocalDate.now().toString();
        }

        return date;
    }

    //Metodo que añade los niveles y niveles completados del usuario a listas, y llama a Printer para imprimirlos
    public static void viewLevels(User u) throws SQLException{
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

        Printer.printLevels(levelList, beatenLevelList, false);
    }

    //Metodo que añade los niveles favoritos a una lista de niveles y llama a Printer para imprimirlos
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

    //Metodo que añade cada tabla a una lista de objetos con atributos iguales a sus columnas. Crea objeto mega (todas las listas) y llama a printer para imprimirlo.
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

        Printer.printAll(new MegaClass(ul, pil, ll, bll, fll, dl), false);
    }

    //Metodo para actualizar datos de la tabla level
    public static void manageLevelUpdate(User u) throws SQLException{
        String query = "";
        String param;
        int paramInt = 0;
        float paramFloat = 0;

        int option = 0;
        String level_name;
        int lid;

        level_name = Scan.scanText("Level name: ");
        lid = DbManager.getLevelId(u, level_name);

        option = InputValidation.checkNumRange(1, 9, option, """
                
                Select an option:
                1. Update game id
                2. Update name
                3. Update creator
                4. Update music
                5. Update difficulty
                6. Update GDDL difficulty
                7. Update attempts
                8. Update start date
                9. Back
                """)
        ;

        query = switch (option) {
            case 1 -> {
                do {
                    paramInt = DbManager.checkGameId(u);
                } while (InputValidation.countChar(11, paramInt));
                yield "UPDATE level SET game_id = '" + paramInt + "' WHERE lid = '" + lid + "';";
            }
            case 2 -> {
                do {
                    param = DbManager.checkLevelName(u);
                } while (InputValidation.countChar(20, param));
                yield "UPDATE level SET level_name = '" + param + "' WHERE lid = '" + lid + "';";
            }
            case 3 -> {
                do {
                    param = Scan.scanText("Creator: ");
                } while (InputValidation.countChar(20, param));
                yield "UPDATE level SET creator = '" + param + "' WHERE lid = '" + lid + "';";
            }
            case 4 -> {
                do {
                    param = Scan.scanText("Music: ");
                } while (InputValidation.countChar(100, param));
                yield "UPDATE level SET music = '" + param + "' WHERE lid = '" + lid + "';";
            }
            case 5 -> {
                paramInt = InputValidation.checkNumRange(1, 5, paramInt, """
                        
                        Choose the difficulty:
                        1. Easy Demon
                        2. Medium Demon
                        3. Hard Demon
                        4. Insane Demon
                        5. Extreme Demon
                        """)
                ;
                yield "UPDATE level SET difficulty = '" + paramInt + "' WHERE lid = '" + lid + "';";
            }
            case 6 -> {
                paramFloat = InputValidation.checkNumRange(1, 35, paramFloat, "Introduce the numerical difficulty (GDDL):");
                yield "UPDATE level SET diff_num = '" + paramFloat + "' WHERE lid = '" + lid + "';";
            }
            case 7 -> {
                do {
                    paramInt = Scan.scanInt("Number of attempts: ");
                } while (InputValidation.countChar(11, paramInt));
                yield "UPDATE level SET attempts = '" + paramInt + "' WHERE lid = '" + lid + "';";
            }
            case 8 -> {
                param = askDate();
                yield "UPDATE level SET start_date = '" + param + "' WHERE lid = '" + lid + "';";
            }
            default -> query;
        };

        if (option != 9){
            DbManager.runSQL(query, false);
            System.out.println("The level was updated correctly");
        }
    }

    //Metodo para borrar datos de la tabla level. Primero borra las dependencias
    public static void deleteLevel(User u) throws SQLException{
        String query;
        int option = 0;

        String level_name;
        int lid;

        level_name = Scan.scanText("Level name: ");
        lid = DbManager.getLevelId(u, level_name);

        option = InputValidation.checkNumRange(1, 2, option, "Are you sure you want to delete this level? (1 = Yes || 2 = No)");

        if (option == 1){
            query = "DELETE FROM fav_demons WHERE lid = '" + lid + "';";
            DbManager.runSQL(query, false);
            query = "DELETE FROM beaten_level WHERE lid = '" + lid + "';";
            DbManager.runSQL(query, false);
            query = "DELETE FROM level WHERE lid = '" + lid + "';";
            DbManager.runSQL(query, false);

            System.out.println("The level was deleted");
        }
    }

    //Metodo para actualizar datos de la tabla beaten_levels
    public static void manageBeatenLevelUpdate(User u) throws SQLException{
        ResultSet rs;
        String query;
        String param;
        int paramInt;
        float paramFloat = 0;

        int option = 0;
        String level_name;
        int lid;

        level_name = Scan.scanText("Level name: ");
        lid = DbManager.getLevelId(u, level_name);

        query = "SELECT blid FROM beaten_level WHERE lid = '" + lid + "';";
        rs = DbManager.runSQL(query, true);

        if (rs.next()){
            option = InputValidation.checkNumRange(1, 7, option, """
                
                Select an option:
                1. Update music rate
                2. Update gameplay rate
                3. Update decoration rate
                4. Update effects rate
                5. Update total attempts
                6. Update end date
                7. Back
                """)
            ;

            query = switch (option) {
                case 1 -> {
                    paramFloat = InputValidation.checkNumRange(0, 10, paramFloat, "Music rate: ");
                    yield "UPDATE beaten_level SET music_rate = '" + paramFloat + "' WHERE lid = '" + lid + "';";
                }
                case 2 -> {
                    paramFloat = InputValidation.checkNumRange(0, 10, paramFloat, "Gameplay rate: ");
                    yield "UPDATE beaten_level SET gameplay_rate = '" + paramFloat + "' WHERE lid = '" + lid + "';";
                }
                case 3 -> {
                    paramFloat = InputValidation.checkNumRange(0, 10, paramFloat, "Decoration rate: ");
                    yield "UPDATE beaten_level SET deco_rate = '" + paramFloat + "' WHERE lid = '" + lid + "';";
                }
                case 4 -> {
                    paramFloat = InputValidation.checkNumRange(0, 10, paramFloat, "Effects rate: ");
                    yield "UPDATE beaten_level SET fx_rate = '" + paramFloat + "' WHERE lid = '" + lid + "';";
                }
                case 5 -> {
                    do {
                        paramInt = Scan.scanInt("Total atempts: ");
                    } while (InputValidation.countChar(11, paramInt));
                    yield "UPDATE beaten_level SET total_attempts = '" + paramInt + "' WHERE lid = '" + lid + "';";
                }
                case 6 -> {
                    param = askDate();
                    yield "UPDATE beaten_level SET end_date = '" + param + "' WHERE lid = '" + lid + "';";
                }
                default -> query;
            };

            if (option != 7){
                DbManager.runSQL(query, false);
                System.out.println("The beaten level was updated correctly");
            }
        } else {
            System.err.println("That level is unbeaten, make sure to beat it first to edit it's ratings");
        }
    }

    //Metodo para des-completar un nivel. Lo borra de beaten_levels e iguala beaten a 0 (de x nivel)
    public static void unBeat(User u) throws SQLException{
        ResultSet rs;
        String query;
        int option = 0;

        String level_name;
        int lid;

        level_name = Scan.scanText("Level name: ");
        lid = DbManager.getLevelId(u, level_name);

        query = "SELECT blid FROM beaten_level WHERE lid = '" + lid + "';";
        rs = DbManager.runSQL(query, true);

        if (rs.next()){
            option = InputValidation.checkNumRange(1, 2, option, "Are you sure you want to un-beat this level? (1 = Yes || 2 = No)");

            if (option == 1){
                query = "UPDATE level SET beaten = 0 WHERE lid = '" + lid + "';";
                DbManager.runSQL(query, false);
                query = "DELETE FROM beaten_level WHERE lid = '" + lid + "';";
                DbManager.runSQL(query, false);
            }

            System.out.println("The level was un-beated");
        } else {
            System.err.println("That level is already marked as unbeated");
        }
    }

    //Metodo para quitar de favoritos un nivel
    public static void unFavourite(User u) throws SQLException{
        ResultSet rs;
        String query;
        int option = 0;

        String level_name;
        int lid;

        level_name = Scan.scanText("Level name: ");
        lid = DbManager.getLevelId(u, level_name);

        query = "SELECT fid FROM fav_demons WHERE lid = '" + lid + "';";
        rs = DbManager.runSQL(query, true);

        if (rs.next()){
            option = InputValidation.checkNumRange(1, 2, option, "Are you sure you want to un-favourite this level? (1 = Yes || 2 = No)");

            if (option == 1) {
                query = "DELETE FROM fav_demons WHERE lid = '" + lid + "';";
                DbManager.runSQL(query, false);
            }

            System.out.println("The level was un-favourited");
        } else {
            System.err.println("That level is already not a favourite");
        }
    }

    //Metodo para actualizar datos de la tabla user
    public static void manageUserUpdate(User u) throws SQLException{
        String query = "";
        String param;
        String repeatParam;

        int option = 0;

        option = InputValidation.checkNumRange(1, 3, option, """
                
                Select an option:
                1. Update username
                2. Update password
                3. Back
                """)
        ;

        query = switch (option) {
            case 1 -> {
                do{
                    param = Scan.scanText("New username: ");
                } while (DbManager.checkDupe("user", "uid", "username", param) && InputValidation.countChar(50, param));
                u.setUsername(param);
                yield "UPDATE user SET username = '" + param + "' WHERE uid = '" + u.getUid() + "';";
            }
            case 2 -> {
                do{
                    do {
                        param = Scan.scanText("New password: ");
                    } while (InputValidation.countChar(30, param));
                    repeatParam = Scan.scanText("Repeat password: ");
                    if (!param.equals(repeatParam)){
                        System.err.println("The passwords do not match, try again");
                    }
                } while (!param.equals(repeatParam));

                param = Encrypt.crypt(param);
                u.setPassword(param);
                yield "UPDATE user SET password = '" + param + "' WHERE uid = '" + u.getUid() + "';";
            }
            default -> query;
        };

        if (option != 3){
            DbManager.runSQL(query, false);
            System.out.println("The information was updated correctly");
        }
    }

    //Metodo para borrar la cuenta. Sale del programa si se acepta (sin System.exit(0))
    public static void deleteUser(User u) throws SQLException{
        ArrayList<Integer> userLevels;
        String query;
        int option = 0;

        userLevels = getUserEntries(u);

        option = InputValidation.checkNumRange(1, 2, option, "Are you sure you want to delete this your account? Tis will delete all your data (1 = Yes || 2 = No)");

        if (option == 1){
            query = "DELETE FROM personal_info WHERE uid = '" + u.getUid() + "';";
            DbManager.runSQL(query, false);
            query = "DELETE FROM fav_demons WHERE uid = '" + u.getUid() + "';";
            DbManager.runSQL(query, false);

            for(Integer integer : userLevels){
                query = "DELETE FROM beaten_level WHERE lid = '" + integer + "';";
                DbManager.runSQL(query, false);
            }

            query = "DELETE FROM level WHERE uid = '" + u.getUid() + "';";
            DbManager.runSQL(query, false);
            query = "DELETE FROM user WHERE uid = '" + u.getUid() + "';";
            DbManager.runSQL(query, false);

            System.out.println("The user was deleted");
            u.setUid(0);
        }
    }

    //Metodo para actualizar datos de la tabla personal_info
    public static void managePersonalInformationUpdate(User u) throws SQLException{
        String query = "";
        String param;

        int option = 0;

        option = InputValidation.checkNumRange(1, 4, option, """
                
                Select an option:
                1. Update name
                2. Update surname
                3. Update email
                4. Back
                """)
        ;

        query = switch (option) {
            case 1 -> {
                do {
                    param = Scan.scanText("Name: ");
                } while (InputValidation.countChar(50, param));
                yield "UPDATE personal_info SET name = '" + param + "' WHERE uid = '" + u.getUid() + "';";
            }
            case 2 -> {
                do {
                    param = Scan.scanText("Surname: ");
                } while (InputValidation.countChar(50, param));
                yield "UPDATE personal_info SET surname = '" + param + "' WHERE uid = '" + u.getUid() + "';";
            }
            case 3 -> {
                do{
                    param = Scan.scanText("E-Mail: ");
                } while (DbManager.checkDupe("personal_info", "uid", "email", param) && InputValidation.countChar(100, param));
                yield "UPDATE personal_info SET email = '" + param + "' WHERE uid = '" + u.getUid() + "';";
            }
            default -> query;
        };

        DbManager.runSQL(query, false);
        System.out.println("The information was updated correctly");
    }

    //Metodo que consige los ids de la tabla level de x usuario y los devuelve en un arraylist
    public static ArrayList<Integer> getUserEntries(User u) throws SQLException{
        ArrayList<Integer> userLevels = new ArrayList<Integer>();
        ResultSet rs;
        String query;

        query = "SELECT lid FROM level WHERE uid = '" + u.getUid() + "';";
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            userLevels.add(rs.getInt("lid"));
        }

        return userLevels;
    }

    //Metodo para hacer una busqueda tipo google de todos los datos string de la BD
    public static void googleSearch() throws SQLException{
        ArrayList<String> searchResult = new ArrayList<String>();
        String query;

        String input;

        input = Scan.scanText("Keyword: ");

        searchResult.add("Usernames: ");
        query = "SELECT username FROM user WHERE username LIKE '%" + input + "%';";
        addToSearchResult(searchResult, query, "username");
        searchResult.add(" ");

        searchResult.add("Names: ");
        query = "SELECT name FROM personal_info WHERE name LIKE '%" + input + "%';";
        addToSearchResult(searchResult, query, "name");
        searchResult.add(" ");

        searchResult.add("Surnames: ");
        query = "SELECT surname FROM personal_info WHERE surname LIKE '%" + input + "%';";
        addToSearchResult(searchResult, query, "surname");
        searchResult.add(" ");

        searchResult.add("E-Mails: ");
        query = "SELECT email FROM personal_info WHERE email LIKE '%" + input + "%';";
        addToSearchResult(searchResult, query, "email");
        searchResult.add(" ");

        searchResult.add("Level names: ");
        query = "SELECT level_name FROM level WHERE level_name LIKE '%" + input + "%';";
        addToSearchResult(searchResult, query, "level_name");
        searchResult.add(" ");

        searchResult.add("Creators: ");
        query = "SELECT creator FROM level WHERE creator LIKE '%" + input + "%';";
        addToSearchResult(searchResult, query, "creator");
        searchResult.add(" ");

        searchResult.add("Music: ");
        query = "SELECT music FROM level WHERE music LIKE '%" + input + "%';";
        addToSearchResult(searchResult, query, "music");
        searchResult.add(" ");

        searchResult.add("Difficulties: ");
        query = "SELECT difficulty_name FROM demon_difficulties WHERE difficulty_name LIKE '%" + input + "%';";
        addToSearchResult(searchResult, query, "difficulty_name");
        searchResult.add(" ");

        Printer.printSearchResult(searchResult);
    }

    //Metodo para añadir un resultado de busqueda (string) de tipo google a X arraylist
    public static void addToSearchResult(ArrayList<String> searchResult, String query, String column) throws SQLException{
        ResultSet rs;
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            searchResult.add(rs.getString(column));
        }
    }

    //Metodo para buscar en un rango de fechas en la tabla level
    public static void dateSearch(User u) throws SQLException{
        LevelList ll = new LevelList();
        BeatenLevelList bll = new BeatenLevelList();
        ResultSet beaten_rs;
        ResultSet rs;
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

        String input;
        String inputT;

        System.out.println("First date: ");
        input = InputValidation.checkDate();
        System.out.println("Second date: ");
        inputT = InputValidation.checkDate();

        query = "SELECT game_id, level_name, creator, music, difficulty_name, diff_num, attempts, beaten, start_date " +
                "FROM level l " +
                "INNER JOIN demon_difficulties d ON l.difficulty = d.did " +
                "WHERE uid = '" + u.getUid() + "' AND start_date BETWEEN '" + input + "' AND '" + inputT + "';";
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

            ll.add(new Level(lid, game_id, level_name, creator, music, difficulty, diff_num, attempts, beaten, start_date));

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

                bll.add(new BeatenLevel(lid, music_rate, gameplay_rate, deco_rate, fx_rate, enjoyment, total_attempts, end_date));
            }
        }

        Printer.printLevels(ll, bll, true);
    }

    //Metodo para encontrar registros nulos. Usa el mismo Printer que viewDataBase()
    public static void findNull() throws SQLException{
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

        query = "SELECT * FROM user " +
                "WHERE uid IS NULL " +
                "OR username IS NULL " +
                "OR password IS NULL;"
        ;
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            uid = rs.getInt("uid");
            username = rs.getString("username");
            password = rs.getString("password");

            ul.add(new User(uid, username, password));
        }

        query = "SELECT username, name, surname, email " +
                "FROM personal_info p " +
                "INNER JOIN user u ON p.uid = u.uid " +
                "WHERE username IS NULL " +
                "OR name IS NULL " +
                "OR surname IS NULL " +
                "OR email IS NULL;"
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
                "INNER JOIN demon_difficulties d ON l.difficulty = d.did " +
                "WHERE lid IS NULL " +
                "OR username IS NULL " +
                "OR game_id IS NULL " +
                "OR level_name IS NULL " +
                "OR creator IS NULL " +
                "OR music IS NULL " +
                "OR difficulty_name IS NULL " +
                "OR diff_num IS NULL " +
                "OR attempts IS NULL " +
                "OR beaten IS NULL " +
                "OR start_date IS NULL;"
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

            query = "SELECT * " +
                    "FROM beaten_Level " +
                    "WHERE lid IS NULL " +
                    "OR music_rate IS NULL " +
                    "OR gameplay_rate IS NULL " +
                    "OR deco_rate IS NULL " +
                    "OR fx_rate IS NULL " +
                    "OR enjoyment IS NULL " +
                    "OR total_attempts IS NULL " +
                    "OR end_date IS NULL;"
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

        query = "SELECT username, level_name " +
                "FROM fav_demons f " +
                "INNER JOIN user u ON f.uid = u.uid " +
                "INNER JOIN level l ON f.lid = l.lid " +
                "WHERE username IS NULL " +
                "OR level_name IS NULL;"
        ;
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            username = rs.getString("username");
            level_name = rs.getString("level_name");

            fll.add(new FavouriteLevel(username, level_name));
        }

        query = "SELECT * FROM demon_difficulties " +
                "WHERE did IS NULL " +
                "OR difficulty_name IS NULL;"
        ;
        rs = DbManager.runSQL(query, true);

        while (rs.next()){
            did = rs.getInt("did");
            difficulty_name = rs.getString("difficulty_name");

            dl.add(new Difficulty(did, difficulty_name));
        }

        Printer.printAll(new MegaClass(ul, pil, ll, bll, fll, dl), true);
    }
}
