package com.example.cloud.pokemon_map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.cloud.pokemon_map.db.Notebook;
import com.example.cloud.pokemon_map.db.Pokemon;
import com.example.cloud.pokemon_map.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;

public class dbtest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest_activity_layout);

//        DataSupport.deleteAll(Pokemon.class);

//        Pokemon addPokemon;
//        addPokemon = new Pokemon();
//        addPokemon.setPokemon_id(7);
//        addPokemon.setPokemon_name("杰尼龟");
//        addPokemon.setPokemon_picture_id(R.drawable.pm_007);
//        addPokemon.setPokemon_intro("甲壳的作用不仅仅是用来保护自己。它圆润的外形和表面的沟槽会减小水的阻力，使它能快速地游动。");
//        addPokemon.setPokemon_type("水");
//        addPokemon.save();

        String TAG = "dbtest";

        Log.d(TAG, "db User ");
        List<User> users = DataSupport.findAll(User.class);
        for (User user:users) {
            Log.d(TAG, "user name is " + user.getUser_name());
        }

        Log.d(TAG, "");
        Log.d(TAG, "");

        Log.d(TAG, "db Pokemon ");
        List<Pokemon> pokemons = DataSupport.findAll(Pokemon.class);
        for (Pokemon pokemon:pokemons) {
            Log.d(TAG, "pokemon name is " + pokemon.getPokemon_name());
        }

        Log.d(TAG, "");
        Log.d(TAG, "");

        Log.d(TAG, "db Notebook ");
        List<Notebook> Notebooks = DataSupport.findAll(Notebook.class);
        for (Notebook notebook:Notebooks) {
            Log.d(TAG, "user name is " + notebook.getNote_title());
        }
    }
}
