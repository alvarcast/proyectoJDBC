package model;

import java.util.ArrayList;

public class BeatenLevelList {
    //Clase para poder acceder desde listas a la tabla beaten_levels

    private ArrayList<BeatenLevel> beatenLevelList = new ArrayList<BeatenLevel>();

    public BeatenLevelList() {}

    public ArrayList<BeatenLevel> getBeatenLevelList() {
        return beatenLevelList;
    }

    public void setBeatenLevelList(ArrayList<BeatenLevel> beatenLevelList) {
        this.beatenLevelList = beatenLevelList;
    }

    public void add(BeatenLevel beatenLevel){
        beatenLevelList.add(beatenLevel);
    }

    public void rm(BeatenLevel beatenLevel){
        beatenLevelList.remove(beatenLevel);
    }

    public void find(BeatenLevel beatenLevel){
        //Find x level
    }
}
