package model;

public class Level {

    private int game_id;
    private String name;
    private String creator;
    private String difficulty;
    private double diff_num;
    private int attempts;
    private boolean beaten;
    private String start_date;

    public Level(int game_id, String name, String creator, String difficulty, double diff_num, int attempts, boolean beaten, String start_date) {
        this.game_id = game_id;
        this.name = name;
        this.creator = creator;
        this.difficulty = difficulty;
        this.diff_num = diff_num;
        this.attempts = attempts;
        this.beaten = beaten;
        this.start_date = start_date;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public double getDiff_num() {
        return diff_num;
    }

    public void setDiff_num(double diff_num) {
        this.diff_num = diff_num;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public boolean isBeaten() {
        return beaten;
    }

    public void setBeaten(boolean beaten) {
        this.beaten = beaten;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
}
