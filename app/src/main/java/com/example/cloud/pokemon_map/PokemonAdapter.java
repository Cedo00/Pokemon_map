package com.example.cloud.pokemon_map;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Cloud on 2018/1/6.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private Context mContext;

    private List<PokemonItem> mPokemonList;


    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView pokemonImage;
        TextView pokemonId;
        TextView pokemonName;

        public ViewHolder(View view) {

            super(view);

            cardView   = (CardView) view;
            pokemonImage = (ImageView) view.findViewById(R.id.item_pokemon_image);
            pokemonId = (TextView) view.findViewById(R.id.item_pokemon_id);
            pokemonName  = (TextView) view.findViewById(R.id.item_pokemon_name);
        }
    }


    public PokemonAdapter(List<PokemonItem> pokemonList) {

        mPokemonList = pokemonList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.pokemon_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                PokemonItem pokemon = mPokemonList.get(position);
//                Intent intent = new Intent(mContext, FruitActivity.class);
//                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
//                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
//                mContext.startActivity(intent);
//            }
//        });
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PokemonItem pokemon = mPokemonList.get(position);
        holder.pokemonId.setText("#00" + pokemon.getPokemon_id());
        holder.pokemonName.setText(pokemon.getPokemon_name());
        Glide.with(mContext).load(pokemon.getPokemon_picture_id()).into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }
}
