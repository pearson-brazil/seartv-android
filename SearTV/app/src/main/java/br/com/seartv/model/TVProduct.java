package br.com.seartv.model;

import android.graphics.Bitmap;

public class TVProduct {

    private Bitmap wallpaper;
    private String title;
    private int year;
    private double grade;

    public Bitmap getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(Bitmap wallpaper) {
        this.wallpaper = wallpaper;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
