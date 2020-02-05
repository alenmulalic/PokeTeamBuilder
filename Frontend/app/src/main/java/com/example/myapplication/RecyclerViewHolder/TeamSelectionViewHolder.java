package com.example.myapplication.RecyclerViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeamSelectionViewHolder extends IViewHolder{
    public TextView pokeName;
    public TextView pokemonNumber;
    public TextView pokemonType1;
    public TextView pokemonType2;
    public CircleImageView pokemonImage;
    public RelativeLayout teamSelectorList;

    public TeamSelectionViewHolder(@NonNull View itemView)
    {
        super(itemView);
        pokemonNumber = itemView.findViewById(R.id.pokeNumber);
        pokemonType1 = itemView.findViewById(R.id.pokemonTYPE1);
        pokemonType2 = itemView.findViewById(R.id.pokemonTYPE2);
        pokemonImage = itemView.findViewById(R.id.pokeTeamimage);
        pokeName = itemView.findViewById(R.id.pokeName);
        teamSelectorList = itemView.findViewById(R.id.teamSelectorList);

    }
}
