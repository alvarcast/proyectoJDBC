package controller;

import model.SingletonDB;
import view.Scan;

import java.sql.*;
import java.util.ArrayList;

public class DbController {

    public static void select(){
        String pattern = "(?i)\\s*SELECT\\s+.*\\s+FROM\\s+\\w+(\\s+WHERE\\s+.*)?\\s*;?\\s*";
        String consult;
        boolean cond;

        do{
            consult = Scan.scanText("Introduzca la sentencia SQL (Select):");

            cond = consult.matches(pattern);

            if (!cond){
                System.err.println("La sentencia introducida no es un SELECT, por favor, inténtelo de nuevo");
            }
        } while (!cond);

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
        ResultSet rs = DbController.runSQL(query, false, true);
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

        SingletonDB instance = SingletonDB.getInstance();

        Connection connection = null;
        ResultSet rs = null;
        Statement query;

        try {
            //Conectar con la base de datos
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + instance.getServername() + "/" + instance.getDatabase(), instance.getUsername(), instance.getPassword());

            query = connection.createStatement();

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
