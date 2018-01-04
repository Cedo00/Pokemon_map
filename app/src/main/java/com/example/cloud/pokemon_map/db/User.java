package com.example.cloud.pokemon_map.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Cloud on 2018/1/3.
 */

public class User extends DataSupport {

    private int id;

    private String user_name;

    private String user_passward;

    private boolean user_gender;

    private boolean user_admin;

    private String user_headshot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_passward() {
        return user_passward;
    }

    public void setUser_passward(String user_passward) {
        this.user_passward = user_passward;
    }

    public boolean isUser_gender() {
        return user_gender;
    }

    public void setUser_gender(boolean user_gender) {
        this.user_gender = user_gender;
    }

    public boolean isUser_admin() {
        return user_admin;
    }

    public void setUser_admin(boolean user_admin) {
        this.user_admin = user_admin;
    }

    public String getUser_headshot() {
        return user_headshot;
    }

    public void setUser_headshot(String user_headshot) {
        this.user_headshot = user_headshot;
    }
}
