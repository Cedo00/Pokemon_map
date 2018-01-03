package com.example.cloud.pokemon_map.db;

/**
 * Created by Cloud on 2018/1/3.
 */

public class pokemon {

    private int pokemon_Id;

    private String pokemon_Name;

    private String pokemon_Picture;

    private String pokemon_Intro;

    private String pokemon_Type;

    public int getPokemon_Id() {
        return pokemon_Id;
    }

    public void setPokemon_Id(int pokemon_Id) {
        this.pokemon_Id = pokemon_Id;
    }

    public String getPokemon_Name() {
        return pokemon_Name;
    }

    public void setPokemon_Name(String pokemon_Name) {
        this.pokemon_Name = pokemon_Name;
    }

    public String getPokemon_Picture() {
        return pokemon_Picture;
    }

    public void setPokemon_Picture(String pokemon_Picture) {
        this.pokemon_Picture = pokemon_Picture;
    }

    public String getPokemon_Intro() {
        return pokemon_Intro;
    }

    public void setPokemon_Intro(String pokemon_Intro) {
        this.pokemon_Intro = pokemon_Intro;
    }

    public String getPokemon_Type() {
        return pokemon_Type;
    }

    public void setPokemon_Type(String pokemon_Type) {
        this.pokemon_Type = pokemon_Type;
    }
}
