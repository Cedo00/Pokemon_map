package com.example.cloud.pokemon_map.db;

/**
 * Created by Cloud on 2018/1/3.
 */

public class note {

    private int note_Id;

    private int note_userId;

    private String note_date;

    private String note_title;

    private String note_content;

    private String note_picture;

    public int getNote_Id() {
        return note_Id;
    }

    public void setNote_Id(int note_Id) {
        this.note_Id = note_Id;
    }

    public int getNote_userId() {
        return note_userId;
    }

    public void setNote_userId(int note_userId) {
        this.note_userId = note_userId;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public String getNote_picture() {
        return note_picture;
    }

    public void setNote_picture(String note_picture) {
        this.note_picture = note_picture;
    }
}
