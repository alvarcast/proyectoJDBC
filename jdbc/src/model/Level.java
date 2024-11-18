package model;

public class Level {
    private int uid;
    private String game_id;
    private String level_name;
    private String creator;
    private String music;
    private int difficulty;
    private float diff_num;
    private int attempts;
    private int beaten;
    private String start_date;

    public Level(int uid, String game_id, String level_name, String creator, String music, int difficulty, float diff_num, int attempts, int beaten, String start_date) {
        this.uid = uid;
        this.game_id = game_id;
        this.level_name = level_name;
        this.creator = creator;
        this.music = music;
        this.difficulty = difficulty;
        this.diff_num = diff_num;
        this.attempts = attempts;
        this.beaten = beaten;
        this.start_date = start_date;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public float getDiff_num() {
        return diff_num;
    }

    public void setDiff_num(float diff_num) {
        this.diff_num = diff_num;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getBeaten() {
        return beaten;
    }

    public void setBeaten(int beaten) {
        this.beaten = beaten;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
}
