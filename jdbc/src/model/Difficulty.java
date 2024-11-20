package model;

public class Difficulty {

    private int did;
    private String diff_name;

    public Difficulty(int did, String diff_name) {
        this.did = did;
        this.diff_name = diff_name;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDiff_name() {
        return diff_name;
    }

    public void setDiff_name(String diff_name) {
        this.diff_name = diff_name;
    }
}
