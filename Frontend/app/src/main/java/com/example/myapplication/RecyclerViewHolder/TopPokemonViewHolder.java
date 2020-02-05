package com.example.myapplication.RecyclerViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopPokemonViewHolder extends IViewHolder {
    public TextView rank;
    public TextView mPokeNO;
    public TextView mPokeName;
    public TextView mPokeT1;
    public TextView mPokeT2;
    public CircleImageView mPokeImage;
    public RelativeLayout topPokemon;

    public TopPokemonViewHolder(@NonNull View itemView)
    {
        super(itemView);
        rank = itemView.findViewById(R.id.pokemonRANKTop);
        mPokeNO = itemView.findViewById(R.id.pokemonNOTop);
        mPokeName = itemView.findViewById(R.id.pokemonNAMETop);
        mPokeT1 = itemView.findViewById(R.id.pokemonTYPE1Top);
        mPokeT2 = itemView.findViewById(R.id.pokemonTYPE2Top);
        mPokeImage = itemView.findViewById(R.id.pokemonIMAGETop);
        topPokemon = itemView.findViewById(R.id.topPokemon);

    }
}
