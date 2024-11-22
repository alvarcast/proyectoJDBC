package controller;

import model.Connex;
import model.User;
import view.Scan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DbManager {

    //Metodo para ejecutar cualquier consulta sql
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

    //Metodo para comprobar si el username o el correo ya estan en uso
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

    //Metodo para comprobas si un nivel ya ha sido pasado
    public static int checkIfBeaten(User u) throws SQLException{
        ResultSet rs;
        String level_name;
        int lid;
        String query;

        level_name = Scan.scanText("Level name: ");

        lid = getLevelId(u, level_name);

        query = "SELECT lid FROM beaten_level WHERE lid = '" + lid + "';";
        rs = DbManager.runSQL(query, true);

        if (rs.next()){
            System.err.println("That level is already marked as beaten");
            lid = -1;
        }

        return lid;
    }

    //Metodo para comprobas si un nivel ya ha sido añadido
    public static String checkLevelName(User u) throws SQLException{
        ResultSet rs;
        String level_name;
        String query;

        level_name = Scan.scanText("Level name: ");

        query = "SELECT level_name FROM level WHERE level_name = '" + level_name + "' AND uid = '" + u.getUid() + "';";
        rs = DbManager.runSQL(query, true);

        if (rs.next()){
            System.err.println("This level was already added, please try again");
        }

        return level_name;
    }

    //Metodo para comprobas si un game_id ya ha esta en uso
    public static int checkGameId(User u) throws SQLException{
        int game_id;
        String query;

        game_id = Scan.scanInt("Game ID: ");

        query = "SELECT game_id FROM level WHERE game_id = '" + game_id + "' AND uid = '" + u.getUid() + "';";
        ResultSet rs = DbManager.runSQL(query, true);

        if (rs.next()){
            System.err.println("This level ID was already added, please try again");
        }

        return game_id;
    }

    //Metodo para comprobas si un nivel ya ha sido añadido a favoritos
    public static int checkFavourite(User u) throws SQLException{
        int lid;
        String query;
        String level_name;

        level_name = Scan.scanText("Level name: ");
        lid = getLevelId(u, level_name);

        query = "SELECT lid FROM fav_demons WHERE lid = '" + lid + "';";
        ResultSet rs = DbManager.runSQL(query, true);

        if (rs.next()){
            System.err.println("This level was already added to favourites");
        } else {
            System.out.println("The level was added correctly to your favourites");
        }

        return lid;
    }

    //Metodo para conseguir el id de un nivel con su nombre
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

    //Metodo para exportar la base de datos a la carpeta sqldump
    //Hecho con chatGPT, stackOverflow, documentación de mysql y cuatro horas de prueba y error
    public static void exportDB(){
        BufferedReader br;
        String line;

        String host = "localhost";
        String port = "3306";
        String user = "root";
        String database = "gd_demons";
        String exportPath = "sqldump/gd_demons_dump.sql";

        //Comando para el process
        String command = String.format(
                "mysqldump --host=%s --port=%s --user=%s %s -r %s",
                host, port, user, database, exportPath
        );

        try {
            //Ejecuto el comando
            Process process = Runtime.getRuntime().exec(command);

            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                System.out.println(line);  //Salida
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Database export successful! File saved to " + exportPath);
            } else {
                System.err.println("Error occurred during export. Exit code: " + exitCode); //Error de mysqldump
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
