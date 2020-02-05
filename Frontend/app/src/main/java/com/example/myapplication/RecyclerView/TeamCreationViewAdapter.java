package com.example.myapplication.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.PokemonActivities.TeamCreation;
import com.example.myapplication.R;
import com.example.myapplication.PokemonActivities.SearchPokemon;
import com.example.myapplication.RecyclerViewHolder.TeamCreationViewHolder;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.myapplication.app.AppController.TAG;

public class TeamCreationViewAdapter extends RecyclerView.Adapter<TeamCreationViewHolder>
{
//    private static final String TAG = "SearchRecyclerViewAdapt";
    private ArrayList<Pokemon> pokemon;
    private Users user;
    private Context mContext;

    public TeamCreationViewAdapter(ArrayList<Pokemon> pokemons, Users user, Context mContext)
    {
        this.pokemon = pokemons;
        this.user = user;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TeamCreationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_teamcreationlist, parent, false);
        TeamCreationViewHolder holder = new TeamCreationViewHolder(view);
        return holder;
    }

    //Setting text and images
    @Override
    public void onBindViewHolder(@NonNull TeamCreationViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");
        final Pokemon currentPokemon = pokemon.get(position);
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

        holder.teamCreationList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, SearchPokemon.class);
                intent.putExtra("userId", user.getUserId());
                intent.putExtra("userTeamId", user.getUserTeamId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    public void setTypeColor(@NonNull TeamCreationViewHolder holder, Pokemon currentPokemon)
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
