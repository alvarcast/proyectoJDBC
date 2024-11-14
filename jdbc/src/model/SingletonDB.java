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

    public static SingletonDB getInstance() {
        if (instance == null) {
            instance = new SingletonDB();
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
