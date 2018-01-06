package com.example.cloud.pokemon_map.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Cloud on 2018/1/3.
 */

public class Pokemon extends DataSupport {

    private int pokemon_id;

    private String pokemon_name;

    private int pokemon_picture_id;

    private String pokemon_intro;

    private String pokemon_type;

    public int getPokemon_id() {
        return pokemon_id;
    }

    public void setPokemon_id(int pokemon_id) {
        this.pokemon_id = pokemon_id;
    }

    public String getPokemon_name() {
        return pokemon_name;
    }

    public void setPokemon_name(String pokemon_name) {
        this.pokemon_name = pokemon_name;
    }

    public int getPokemon_picture_id() {
        return pokemon_picture_id;
    }

    public void setPokemon_picture_id(int pokemon_picture_id) {
        this.pokemon_picture_id = pokemon_picture_id;
    }

    public String getPokemon_intro() {
        return pokemon_intro;
    }

    public void setPokemon_intro(String pokemon_intro) {
        this.pokemon_intro = pokemon_intro;
    }

    public String getPokemon_type() {
        return pokemon_type;
    }

    public void setPokemon_type(String pokemon_type) {
        this.pokemon_type = pokemon_type;
    }
}
