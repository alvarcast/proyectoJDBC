package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Db{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", database='" + database + '\'' +
                ", servername='" + servername + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBase db = (DataBase) o;
        return Objects.equals(username, db.username) && Objects.equals(password, db.password) && Objects.equals(database, db.database) && Objects.equals(servername, db.servername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, database, servername);
    }

    public void consult(String sql, boolean view) {

        Connection conexion = null;
        Statement sentenciaSQL;

        try {
            //Conectar con la base de datos
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://" + servername + "/" + database, username, password);

            sentenciaSQL = conexion.createStatement();
            ResultSet rs = sentenciaSQL.executeQuery(sql);

            System.out.println("Consulta realizada correctamente");

            if (view){
                //Imprimir select
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }finally {
            try{
                if (conexion != null){
                    conexion.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
