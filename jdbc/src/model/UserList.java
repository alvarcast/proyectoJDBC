package model;

import java.util.ArrayList;

public class UserList {

    private ArrayList<User> userList = new ArrayList<User>();

    public UserList() {}

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public void add(User user){
        userList.add(user);
    }

    public void rm(User user){
        userList.remove(user);
    }

    public void find(User user){
        //Find x level
    }
}
