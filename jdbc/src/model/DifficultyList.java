package model;

import java.util.ArrayList;

public class DifficultyList {

    private ArrayList<Difficulty> difficultieList = new ArrayList<Difficulty>();

    public DifficultyList() {}

    public ArrayList<Difficulty> getDifficultieList() {
        return difficultieList;
    }

    public void setDifficultieList(ArrayList<Difficulty> difficultieList) {
        this.difficultieList = difficultieList;
    }

    public void add(Difficulty difficulty){
        difficultieList.add(difficulty);
    }

    public void rm(Difficulty difficulty){
        difficultieList.remove(difficulty);
    }

    public void find(Difficulty difficulty){
        //Find x level
    }
}
