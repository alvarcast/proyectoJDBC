package model;

public final class SingletonDB {
    //Clase singleton. Instancia de las credenciales de acceso a la base de datos

    private static SingletonDB instance;

    private final String username;
    private final String password;
    private final String database;
    private final String servername;

    private SingletonDB(){
        this.username = "administrador";
        this.password = "contraseñaAdministrador";
        this.database = "gd_demons";
        this.servername = "localhost";
    }

    public SingletonDB(String username, String password, String database, String servername) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.servername = servername;
    }

    public static SingletonDB getInstance() {
        if (instance == null) {
            instance = new SingletonDB();
        }
        return instance;
    }

    public static SingletonDB getInstance(String username, String password, String database, String servername) {
        if (instance == null) {
            instance = new SingletonDB(username, password, database, servername);
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public String getServername() {
        return servername;
    }
}
