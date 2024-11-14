package model;

public final class SingletonDB {

    private static SingletonDB instance;

    private final String username;
    private final String password;
    private final String database;
    private final String servername;

    private SingletonDB(){
        this.username = "user";
        this.password = "1234";
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
