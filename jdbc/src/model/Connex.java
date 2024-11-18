package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connex {

    private static Connex instance;

    private Connection connection = null;

    public Connex(String username, String password, String database, String servername) {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + servername + "/" + database, username, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Connex getInstance(String username, String password, String database, String servername) {
        if (instance == null) {
            instance = new Connex(username, password, database, servername);
        }
        return instance;
    }

    public static Connex getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void close(){
        try {
            if (Connex.getInstance() != null){
                if (Connex.getInstance().getConnection() != null){
                    Connex.getInstance().getConnection().close();
                }
            }
            System.out.println("Conexión cerrada correctamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
