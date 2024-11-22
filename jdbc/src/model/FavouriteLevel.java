package model;

public class FavouriteLevel {
    //Clase para entradas de la tabla fav_demons

    private String username;
    private String level_name;

    public FavouriteLevel(String username, String level_name) {
        this.username = username;
        this.level_name = level_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }
}
