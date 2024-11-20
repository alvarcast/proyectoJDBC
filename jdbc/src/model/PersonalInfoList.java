package model;

import java.util.ArrayList;

public class PersonalInfoList {

    private ArrayList<PersonalInfo> personalInfoList = new ArrayList<PersonalInfo>();

    public PersonalInfoList() {}

    public ArrayList<PersonalInfo> getPersonalInfoList() {
        return personalInfoList;
    }

    public void setPersonalInfoList(ArrayList<PersonalInfo> personalInfoList) {
        this.personalInfoList = personalInfoList;
    }

    public void add(PersonalInfo personalInfo){
        personalInfoList.add(personalInfo);
    }

    public void rm(PersonalInfo personalInfo){
        personalInfoList.remove(personalInfo);
    }

    public void find(PersonalInfo personalInfo){
        //Find x level
    }
}
