package com.example.myapplication.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.PokemonActivities.PokemonInfoPage;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewHolder.RecommendViewHolder;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecommendViewAdapter extends RecyclerView.Adapter<RecommendViewHolder>
{
    private ArrayList<Pokemon> mPokemonList;
    private Users user;
    private Context mContext;

    public RecommendViewAdapter(ArrayList<Pokemon> pokemonList, Users user, Context context)
    {
        mPokemonList = pokemonList;
        this.user = user;
        mContext = context;
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recommendlist, parent, false);
        RecommendViewHolder svh = new RecommendViewHolder(view);
        return svh;
    }

    //Setting text and images
    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder holder, int position)
    {
        final Pokemon currentPokemon = mPokemonList.get(position);
        holder.mPokeNO.setText(currentPokemon.getNumber());
        holder.mPokeNO.setTextColor(Color.WHITE);
        holder.mPokeName.setText(currentPokemon.getName());
        holder.mPokeName.setTextColor(Color.WHITE);
        holder.mPokeT1.setText(currentPokemon.getType1());
        holder.mPokeT1.setTextColor(Color.WHITE);
        holder.mPokeT2.setText(currentPokemon.getType2());
        holder.mPokeT2.setTextColor(Color.WHITE);
        Glide.with(mContext)
                .asBitmap()
                .load(currentPokemon.getImage())
                .into(holder.mPokeImage);

        holder.mPokeT1.setBackgroundColor(currentPokemon.getTypeColor(currentPokemon.getType1().toLowerCase(), mContext));
        holder.mPokeT2.setBackgroundColor(currentPokemon.getTypeColor(currentPokemon.getType2().toLowerCase(), mContext));
        if(currentPokemon.getTypeColor(currentPokemon.getType2().toLowerCase(), mContext) ==
                currentPokemon.getTypeColor(currentPokemon.getType1().toLowerCase(), mContext)){
            holder.mPokeT2.setText("");
            holder.mPokeT2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.pokeballRed));
        }

        holder.recommendLayout.setOnClickListener(new View.OnClickListener() {

            //Click to add or delete pokemon
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PokemonInfoPage.class);
                intent.putExtra("Pokemon", currentPokemon);
                intent.putExtra("User", user);
                intent.putExtra("addOrDelete", "add");
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mPokemonList.size();
    }

}

