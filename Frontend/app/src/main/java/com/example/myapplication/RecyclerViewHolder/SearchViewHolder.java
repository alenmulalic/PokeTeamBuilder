package com.example.myapplication.RecyclerViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchViewHolder extends IViewHolder {
    public TextView mPokeNO;
    public TextView mPokeName;
    public TextView mPokeT1;
    public TextView mPokeT2;
    public CircleImageView mPokeImage;
    public RelativeLayout searchLayout;

    public SearchViewHolder(@NonNull View itemView)
    {
        super(itemView);
        mPokeNO = itemView.findViewById(R.id.pokemonNO);
        mPokeName = itemView.findViewById(R.id.pokemonNAME);
        mPokeT1 = itemView.findViewById(R.id.pokemonTYPE1);
        mPokeT2 = itemView.findViewById(R.id.pokemonTYPE2);
        mPokeImage = itemView.findViewById(R.id.pokemonIMAGE);
        searchLayout = itemView.findViewById(R.id.searchLayout);

    }
}
