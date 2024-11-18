package controller;

import model.Connex;
import model.SingletonDB;

import java.sql.*;
import java.util.ArrayList;

public class DbManager {

    public static void select(){


    }

    public static void create(String tablename, ArrayList<String> data){
        String consult = "CREATE TABLE " + tablename;
    }

    public static void insert(String tablename){

    }

    public static void update(String tablename, String clause, String condition){

    }

    public static void delete(String tablename, String clause, String condition){
        String consult = "DELETE " + clause + " FROM " + tablename + " WHERE " + condition;
    }

    public static void truncate(){

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

    public static ResultSet runSQL(String sql, boolean view, boolean execQuery) {

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

            if (execQuery){
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

    public static void disconnect(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
