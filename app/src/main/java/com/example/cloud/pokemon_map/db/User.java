package com.example.cloud.pokemon_map.db;

/**
 * Created by Cloud on 2018/1/3.
 */

public class User {

    private int user_Id;

    private String user_Name;

    private String user_Passward;

    private boolean user_Gender;

    private boolean user_isAdmin;

    private String user_Headshot;

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Passward() {
        return user_Passward;
    }

    public void setUser_Passward(String user_Passward) {
        this.user_Passward = user_Passward;
    }

    public boolean isUser_Gender() {
        return user_Gender;
    }

    public void setUser_Gender(boolean user_Gender) {
        this.user_Gender = user_Gender;
    }

    public boolean isUser_isAdmin() {
        return user_isAdmin;
    }

    public void setUser_isAdmin(boolean user_isAdmin) {
        this.user_isAdmin = user_isAdmin;
    }

    public String getUser_Headshot() {
        return user_Headshot;
    }

    public void setUser_Headshot(String user_Headshot) {
        this.user_Headshot = user_Headshot;
    }
}
