package model;

public class BeatenLevel {

    private double music_rate;
    private double gameplay_rate;
    private double deco_rate;
    private double fx_rate;
    private double enjoyment;
    private int total_attempts;
    private String end_date;

    public BeatenLevel(double music_rate, double gameplay_rate, double deco_rate, double fx_rate, int totalAttempts, String end_date) {
        this.music_rate = music_rate;
        this.gameplay_rate = gameplay_rate;
        this.deco_rate = deco_rate;
        this.fx_rate = fx_rate;
        this.enjoyment = (this.music_rate + this.gameplay_rate + this.deco_rate + this.fx_rate) / 4;
        this.total_attempts = totalAttempts;
        this.end_date = end_date;
    }

    public double getMusic_rate() {
        return music_rate;
    }

    public void setMusic_rate(double music_rate) {
        this.music_rate = music_rate;
    }

    public double getGameplay_rate() {
        return gameplay_rate;
    }

    public void setGameplay_rate(double gameplay_rate) {
        this.gameplay_rate = gameplay_rate;
    }

    public double getDeco_rate() {
        return deco_rate;
    }

    public void setDeco_rate(double deco_rate) {
        this.deco_rate = deco_rate;
    }

    public double getFx_rate() {
        return fx_rate;
    }

    public void setFx_rate(double fx_rate) {
        this.fx_rate = fx_rate;
    }

    public double getEnjoyment() {
        return enjoyment;
    }

    public void setEnjoyment(double enjoyment) {
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
