package com.example.cloud.pokemon_map;

/**
 * Created by Cloud on 2018/1/6.
 */

public class PokemonItem {

    private int pokemon_id;

    private String pokemon_name;

    private int pokemon_picture_id;

    private String pokemon_intro;

    private String pokemon_type;

    public PokemonItem(int pokemon_id,       String pokemon_name, int pokemon_picture_id,
                       String pokemon_intro, String pokemon_type) {
        this.pokemon_id         = pokemon_id;
        this.pokemon_name       = pokemon_name;
        this.pokemon_picture_id = pokemon_picture_id;
        this.pokemon_intro      = pokemon_intro;
        this.pokemon_type       = pokemon_type;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public String getPokemon_name() {
        return pokemon_name;
    }

    public int getPokemon_picture_id() {
        return pokemon_picture_id;
    }

    public String getPokemon_intro() {
        return pokemon_intro;
    }

    public String getPokemon_type() {
        return pokemon_type;
    }
}
