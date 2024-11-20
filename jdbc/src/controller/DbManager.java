package controller;

import model.Connex;
import model.User;
import view.Scan;

import java.sql.*;

public class DbManager {

    public static ResultSet runSQL(String sql, boolean noUpdate) {
        Connex connex_instance;

        ResultSet rs = null;
        Statement query;

        try {
            //Conectar con la base de datos
            Class.forName("com.mysql.jdbc.Driver");
            connex_instance = Connex.getInstance();

            query = connex_instance.getConnection().createStatement();

            if (noUpdate){
                rs = query.executeQuery(sql);
            } else {
                query.executeUpdate(sql);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public static boolean checkDupe(String table, String tableReturn, String column, String param) throws SQLException{
        ResultSet rs;
        String query;
        boolean dupe = true;

        query = "SELECT " + tableReturn + " FROM " + table + " WHERE " + column + " = '" + param + "';";
        rs = DbManager.runSQL(query, true);

        if (rs.next()){
            System.err.println("That " + column + " is already in use, please try again.");
        } else {
            dupe = false;
        }

        return dupe;
    }

    public static int checkIfBeaten(User u) throws SQLException{
        ResultSet rs;
        String level_name;
        int lid;
        String query;
        boolean out = false;

        level_name = Scan.scanText("Level name: ");

        do{
            lid = getLevelId(u, level_name);

            query = "SELECT lid FROM beaten_level WHERE lid = '" + lid + "';";
            rs = DbManager.runSQL(query, true);

            if (rs.next()){
                System.err.println("That level is already marked as beaten, please try again.");
                level_name = Scan.scanText("Level name: ");
            } else {
                out = true;
            }
        } while (!out);

        return lid;
    }

    public static String checkLevelName(User u) throws SQLException{
        ResultSet rs;
        String level_name;
        String query;
        boolean out = false;

        level_name = Scan.scanText("Level name: ");

        do{
            query = "SELECT level_name FROM level WHERE level_name = '" + level_name + "' AND uid = '" + u.getUid() + "';";
            rs = DbManager.runSQL(query, true);

            if (rs.next()){
                System.err.println("This level was already added, please try again");
                level_name = Scan.scanText("Level name: ");
            } else {
                out = true;
            }
        } while (!out);

        return level_name;
    }

    public static int checkGameId(User u) throws SQLException{
        int game_id;
        String query;
        boolean out = false;

        game_id = Scan.scanInt("Game ID: ");

        do{
            query = "SELECT game_id FROM level WHERE game_id = '" + game_id + "' AND uid = '" + u.getUid() + "';";
            ResultSet rs = DbManager.runSQL(query, true);

            if (rs.next()){
                System.err.println("This level ID was already added, please try again");
                game_id = Scan.scanInt("Game ID: ");
            } else {
                out = true;
            }
        } while (!out);

        return game_id;
    }

    public static int checkFavourite(User u) throws SQLException{
        int lid;
        String query;
        String level_name;
        boolean out = false;

        level_name = Scan.scanText("Level name: ");
        lid = getLevelId(u, level_name);

        do{
            query = "SELECT lid FROM fav_demons WHERE lid = '" + lid + "';";
            ResultSet rs = DbManager.runSQL(query, true);

            if (rs.next()){
                System.err.println("This level ID was already added, please try again");
                level_name = Scan.scanText("Level name: ");
                lid = getLevelId(u, level_name);
            } else {
                out = true;
            }
        } while (!out);

        return lid;
    }

    public static int getLevelId(User u, String level_name) throws SQLException{
        ResultSet rs;
        int lid = 0;
        boolean out = false;

        do{
            rs = DbManager.runSQL("SELECT lid FROM level WHERE level_name = '" + level_name + "' AND uid = '" + u.getUid() + "';", true);
            if (rs.next()){
                lid = ((Number) rs.getObject(1)).intValue();
                out = true;
            } else {
                System.err.println("That level entry doesn't exist for this user, please try again");
                level_name = Scan.scanText("Level name: ");
            }
        }while (!out);

        return lid;
    }
}
