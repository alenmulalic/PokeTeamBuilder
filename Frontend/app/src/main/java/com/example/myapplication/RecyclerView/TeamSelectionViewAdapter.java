package com.example.myapplication.RecyclerView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myapplication.PokemonActivities.PokemonInfoPage;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewHolder.TeamSelectionViewHolder;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import java.util.ArrayList;

public class TeamSelectionViewAdapter extends RecyclerView.Adapter<TeamSelectionViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private Users user;
    private Context mContext;

    public TeamSelectionViewAdapter(ArrayList<Pokemon> pokemons, Users user, Context mContext) {
        this.pokemons = pokemons;
        this.user = user;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TeamSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        TeamSelectionViewHolder viewHolder = new TeamSelectionViewHolder(view);
        return viewHolder;
    }

    //Setting text and images
    @Override
    public void onBindViewHolder(@NonNull TeamSelectionViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final Pokemon currentPokemon = pokemons.get(position);
        Glide.with(mContext)
                .asBitmap()
                .load(currentPokemon.getImage())
                .into(holder.pokemonImage);

        holder.pokeName.setText(currentPokemon.getName());
        holder.pokeName.setTextColor(Color.WHITE);

        holder.pokemonNumber.setText(currentPokemon.getNumber());
        holder.pokemonNumber.setTextColor(Color.WHITE);

        holder.pokemonType1.setText(currentPokemon.getType1());
        holder.pokemonType1.setTextColor(Color.WHITE);

        holder.pokemonType2.setText(currentPokemon.getType2());
        holder.pokemonType2.setTextColor(Color.WHITE);

        setTypeColor(holder, currentPokemon);

        holder.teamSelectorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked On Image" + pokemons.get(position));
                Pokemon currentPokemon = pokemons.get(position);
                Intent intent = new Intent(mContext, PokemonInfoPage.class);
                intent.putExtra("Pokemon", currentPokemon);
                intent.putExtra("User", user);
                intent.putExtra("addOrDelete", "delete");
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void setTypeColor(@NonNull TeamSelectionViewHolder holder, Pokemon currentPokemon)
    {
        String type1 = currentPokemon.getType1().toLowerCase();
        String type2 = currentPokemon.getType2().toLowerCase();

        holder.pokemonType1.setBackgroundColor(currentPokemon.getTypeColor(type1, mContext));
        holder.pokemonType2.setBackgroundColor(currentPokemon.getTypeColor(type2, mContext));
        if(type2.equals(type1)){
            holder.pokemonType2.setText("");
            holder.pokemonType2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.pokeballRed));
        }
    }
}
