package com.example.cloud.pokemon_map.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Cloud on 2018/1/3.
 */

public class Notebook extends DataSupport {

    private String note_username;

    private String note_date;

    private String note_title;

    private String note_content;

    private String note_picture;

    public String getNote_username() {
        return note_username;
    }

    public void setNote_username(String note_username) {
        this.note_username = note_username;
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
