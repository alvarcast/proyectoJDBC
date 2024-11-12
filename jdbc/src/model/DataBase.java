package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class DataBase {

    private String username;
    private String password;
    private String database;
    private String servername;

    public DataBase(String username, String password, String database, String servername) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.servername = servername;
    }

    public DataBase(String username, String password, String database) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.servername = "localhost";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public ResultSet consult(String sql, boolean view) {

        Connection connection = null;
        ResultSet rs = null;
        Statement query;

        try {
            //Conectar con la base de datos
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + servername + "/" + database, username, password);

            query = connection.createStatement();
            rs = query.executeQuery(sql);

            System.out.println("Consulta realizada correctamente");

            if (view){
                //Imprimir select
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }finally {
            try{
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rs;
    }
}
