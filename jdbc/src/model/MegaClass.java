package model;

public class MegaClass {

    private UserList userList;
    private PersonalInfoList personalInfoList;
    private LevelList levelList;
    private BeatenLevelList beatenLevelList;
    private FavouriteLevelList favouriteLevelList;
    private DifficultyList difficultyList;

    public MegaClass(UserList userList, PersonalInfoList personalInfoList, LevelList levelList, BeatenLevelList beatenLevelList, FavouriteLevelList favouriteLevelList, DifficultyList difficultyList) {
        this.userList = userList;
        this.personalInfoList = personalInfoList;
        this.levelList = levelList;
        this.beatenLevelList = beatenLevelList;
        this.favouriteLevelList = favouriteLevelList;
        this.difficultyList = difficultyList;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public PersonalInfoList getPersonalInfoList() {
        return personalInfoList;
    }

    public void setPersonalInfoList(PersonalInfoList personalInfoList) {
        this.personalInfoList = personalInfoList;
    }

    public LevelList getLevelList() {
        return levelList;
    }

    public void setLevelList(LevelList levelList) {
        this.levelList = levelList;
    }

    public BeatenLevelList getBeatenLevelList() {
        return beatenLevelList;
    }

    public void setBeatenLevelList(BeatenLevelList beatenLevelList) {
        this.beatenLevelList = beatenLevelList;
    }

    public FavouriteLevelList getFavouriteLevelList() {
        return favouriteLevelList;
    }

    public void setFavouriteLevelList(FavouriteLevelList favouriteLevelList) {
        this.favouriteLevelList = favouriteLevelList;
    }

    public DifficultyList getDifficultyList() {
        return difficultyList;
    }

    public void setDifficultyList(DifficultyList difficultyList) {
        this.difficultyList = difficultyList;
    }

    public boolean isEmpty(){
        boolean empty;
        empty = userList.getUserList().isEmpty() &&
                personalInfoList.getPersonalInfoList().isEmpty() &&
                levelList.getLevelList().isEmpty() &&
                beatenLevelList.getBeatenLevelList().isEmpty() &&
                favouriteLevelList.getFavouriteLevelList().isEmpty() &&
                difficultyList.getDifficultieList().isEmpty()
        ;
        return empty;
    }
}
