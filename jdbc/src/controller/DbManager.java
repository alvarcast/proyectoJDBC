package controller;

import model.Connex;
import model.SingletonDB;
import model.User;
import view.Scan;

import java.sql.*;
import java.util.ArrayList;

public class DbManager {

    public static ResultSet runSQL(String sql, boolean view, boolean noUpdate) {

        SingletonDB db_instance = SingletonDB.getInstance();
        Connex connex_instance;

        Connection connection;
        ResultSet rs = null;
        Statement query;

        try {
            //Conectar con la base de datos
            Class.forName("com.mysql.jdbc.Driver");
            connex_instance = Connex.getInstance(db_instance.getUsername(), db_instance.getPassword(), db_instance.getDatabase(), db_instance.getServername());

            query = connex_instance.getConnection().createStatement();

            if (noUpdate){
                rs = query.executeQuery(sql);
            } else {
                query.executeUpdate(sql);
            }

            if (view){
                //Imprimir select
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public static boolean checkDupe(String table, String tableReturn, String column, String param){
        String query = "SELECT " + tableReturn + " FROM " + table + " WHERE " + column + " = '" + param + "';";
        ResultSet rs = DbManager.runSQL(query, false, true);
        boolean dupe = true;

        try {
            if (rs.next()){
                System.err.println("That " + column + " is already in use, please try again.");
            } else {
                dupe = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dupe;
    }

    public static int getLevelId(User u) throws SQLException{
        ResultSet rs;
        int lid = 0;
        String level_name;
        boolean out = false;

        do{
            level_name = Scan.scanText("Level name:");

            rs = DbManager.runSQL("SELECT id FROM level WHERE level_name = '" + level_name + "' AND uid = '" + u.getUid() + "';", false, true);
            if (rs.next()){
                lid = ((Number) rs.getObject(1)).intValue();
                out = true;
            } else {
                System.err.println("That level entry doesn't exist for this user, please try again");
            }
        }while (!out);

        return lid;
    }
}
