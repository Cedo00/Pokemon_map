package com.example.cloud.pokemon_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cloud.pokemon_map.db.Pokemon;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PokemonItemActivity extends AppCompatActivity {

    public static void actionStart(Context context, String pokemonName) {
        Intent intent = new Intent(context, PokemonItemActivity.class);
        intent.putExtra("pokemonName", pokemonName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_item_activity_layout);

        Intent intent = getIntent();
        String pokemonName = intent.getStringExtra("pokemonName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.pm_item_toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.pm_item_collapsing_toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // 设置宝可梦信息
        ImageView img_pm_image = (ImageView) findViewById(R.id.pm_item_image_view);
        TextView tx_pm_name = (TextView) findViewById(R.id.pm_item_name_text);
        TextView tx_pm_id = (TextView) findViewById(R.id.pm_item_id_text);
        TextView tx_pm_intro = (TextView) findViewById(R.id.pm_item_intro_text);
        TextView tx_pm_type = (TextView) findViewById(R.id.pm_item_type_text);

        String pm_name = "";
        int pm_id = 0;
        int pm_image_id = 0;
        String pm_intro = "";
        String pm_type = "";

        List<Pokemon> pokemons = DataSupport.where("pokemon_name = ?", pokemonName).find(Pokemon.class);
        for (Pokemon pokemon: pokemons) {
            Log.d("PokemonItemActivity", "pokemon id is " + pokemon.getPokemon_id());

            pm_name = pokemon.getPokemon_name();
            pm_id = pokemon.getPokemon_id();
            pm_image_id = pokemon.getPokemon_picture_id();
            pm_intro = pokemon.getPokemon_intro();
            pm_type = pokemon.getPokemon_type();
        }

        collapsingToolbar.setTitle(pm_name);
        Glide.with(this).load(pm_image_id).into(img_pm_image);
        tx_pm_name.setText(pm_name);
        tx_pm_id.setText("#00" + pm_id);
        tx_pm_intro.setText(pm_intro);
        tx_pm_type.setText(pm_type);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
