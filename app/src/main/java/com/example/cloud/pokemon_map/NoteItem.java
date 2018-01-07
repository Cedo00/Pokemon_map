package com.example.cloud.pokemon_map;

/**
 * Created by Cloud on 2018/1/6.
 */

public class NoteItem {

    private String note_user;

    private int note_id;

    private String note_date;

    private String note_title;

    private String note_content;

    private String note_picture;

    public NoteItem(String note_user, int note_id, String note_date, String note_title, String note_content, String note_picture) {
        this.note_user = note_user;
        this.note_id = note_id;
        this.note_date = note_date;
        this.note_title = note_title;
        this.note_content = note_content;
        this.note_picture = note_picture;
    }

    public int getNote_id() {
        return note_id;
    }

    public String getNote_user() {
        return note_user;
    }

    public String getNote_date() {
        return note_date;
    }

    public String getNote_title() {
        return note_title;
    }

    public String getNote_content() {
        return note_content;
    }

    public String getNote_picture() {
        return note_picture;
    }
}
