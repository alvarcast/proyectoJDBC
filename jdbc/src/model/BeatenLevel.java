package model;

public class BeatenLevel {

    private int lid;
    private float music_rate;
    private float gameplay_rate;
    private float deco_rate;
    private float fx_rate;
    private float enjoyment;
    private int total_attempts;
    private String end_date;

    public BeatenLevel(int lid, float music_rate, float gameplay_rate, float deco_rate, float fx_rate, float enjoyment, int total_attempts, String end_date) {
        this.lid = lid;
        this.music_rate = music_rate;
        this.gameplay_rate = gameplay_rate;
        this.deco_rate = deco_rate;
        this.fx_rate = fx_rate;
        this.enjoyment = enjoyment;
        this.total_attempts = total_attempts;
        this.end_date = end_date;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public float getMusic_rate() {
        return music_rate;
    }

    public void setMusic_rate(float music_rate) {
        this.music_rate = music_rate;
    }

    public float getGameplay_rate() {
        return gameplay_rate;
    }

    public void setGameplay_rate(float gameplay_rate) {
        this.gameplay_rate = gameplay_rate;
    }

    public float getDeco_rate() {
        return deco_rate;
    }

    public void setDeco_rate(float deco_rate) {
        this.deco_rate = deco_rate;
    }

    public float getFx_rate() {
        return fx_rate;
    }

    public void setFx_rate(float fx_rate) {
        this.fx_rate = fx_rate;
    }

    public float getEnjoyment() {
        return enjoyment;
    }

    public void setEnjoyment(float enjoyment) {
        this.enjoyment = enjoyment;
    }

    public int getTotal_attempts() {
        return total_attempts;
    }

    public void setTotal_attempts(int total_attempts) {
        this.total_attempts = total_attempts;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
