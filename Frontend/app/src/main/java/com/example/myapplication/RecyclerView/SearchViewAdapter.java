package com.example.myapplication.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.PokemonActivities.PokemonInfoPage;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewHolder.SearchViewHolder;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewHolder> implements Filterable
{
    private ArrayList<Pokemon> mPokemonList;
    private ArrayList<Pokemon> mPokemonListFull;
    private Context mContext;
    private Users user;

    public SearchViewAdapter(ArrayList<Pokemon> pokemonList, Users user, Context context)
    {
        mPokemonList = pokemonList;
        mPokemonListFull = new ArrayList<>(pokemonList);
        this.user = user;
        mContext = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_searchlistpokemon, parent, false);
        SearchViewHolder svh = new SearchViewHolder(view);
        return svh;
    }

    //Setting text and images
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position)
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

        setTypeColor(holder, currentPokemon);

        holder.searchLayout.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public Filter getFilter() {
        return pokemonFilter;
    }

    // Creating the filter based on the user input
    private Filter pokemonFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Pokemon> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() < 0)
            {
                filteredList.addAll(mPokemonListFull);
            }
            else
            {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Pokemon pokemon : mPokemonListFull)
                {
                    if(pokemon.getName().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(pokemon);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            mPokemonList.clear();
            mPokemonList.addAll((ArrayList<Pokemon>)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public void setTypeColor(@NonNull SearchViewHolder holder, Pokemon currentPokemon)
    {
        String type1 = currentPokemon.getType1().toLowerCase();
        String type2 = currentPokemon.getType2().toLowerCase();

        holder.mPokeT1.setBackgroundColor(currentPokemon.getTypeColor(type1, mContext));
        holder.mPokeT2.setBackgroundColor(currentPokemon.getTypeColor(type2, mContext));
        if(type2.equals(type1)){
            holder.mPokeT2.setText("");
            holder.mPokeT2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.pokeballRed));
        }
    }
}