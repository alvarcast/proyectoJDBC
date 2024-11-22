package model;

import java.util.ArrayList;

public class LevelList {
    //Clase para poder acceder desde listas a la tabla level

    private ArrayList<Level> levelList = new ArrayList<Level>();

    public LevelList() {}

    public ArrayList<Level> getLevelList() {
        return levelList;
    }

    public void setLevelList(ArrayList<Level> levelList) {
        this.levelList = levelList;
    }

    public void add(Level level){
        levelList.add(level);
    }

    public void rm(Level level){
        levelList.remove(level);
    }

    public void find(Level level){
        //Find x level
    }
}
