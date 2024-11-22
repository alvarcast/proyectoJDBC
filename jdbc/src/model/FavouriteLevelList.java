package model;

import java.util.ArrayList;

public class FavouriteLevelList {
    //Clase para poder acceder desde listas a la tabla fav_demons

    private ArrayList<FavouriteLevel> favouriteLevelList = new ArrayList<FavouriteLevel>();

    public FavouriteLevelList() {}

    public ArrayList<FavouriteLevel> getFavouriteLevelList() {
        return favouriteLevelList;
    }

    public void setFavouriteLevelList(ArrayList<FavouriteLevel> favouriteLevelList) {
        this.favouriteLevelList = favouriteLevelList;
    }

    public void add(FavouriteLevel favouriteLevel){
        favouriteLevelList.add(favouriteLevel);
    }

    public void rm(FavouriteLevel favouriteLevel){
        favouriteLevelList.remove(favouriteLevel);
    }

    public void find(FavouriteLevel favouriteLevel){
        //Find x level
    }
}
