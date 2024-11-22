package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connex {
    //Clase singleton para la conexion. Usa la clase SingletonDB para las credenciales. Muy util ya que puedo ccerrar la instancia de la conexion desde donde quiera con el metodo close()

    private static Connex instance;

    private Connection connection = null;

    public Connex(String username, String password, String database, String servername) {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + servername + "/" + database, username, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void getInstance(String username, String password, String database, String servername) {
        if (instance == null) {
            instance = new Connex(username, password, database, servername);
        }
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
            System.out.println("Connection closed succesfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
