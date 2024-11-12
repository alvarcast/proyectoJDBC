package model;

public class PersonalInfo {

    private int uid;
    private String name;
    private String surname;
    private  String email;
    private String rec_email;

    public PersonalInfo(int uid, String name, String surname, String email, String rec_email) {
        this.uid = uid;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.rec_email = rec_email;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRec_email() {
        return rec_email;
    }

    public void setRec_email(String rec_email) {
        this.rec_email = rec_email;
    }
}
