package com.example.myapplication.RecyclerViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecommendViewHolder extends IViewHolder{

    public TextView mPokeNO;
    public TextView mPokeName;
    public TextView mPokeT1;
    public TextView mPokeT2;
    public CircleImageView mPokeImage;
    public RelativeLayout recommendLayout;

    public RecommendViewHolder(@NonNull View itemView)
    {
        super(itemView);
        mPokeNO = itemView.findViewById(R.id.pokemonNO);
        mPokeName = itemView.findViewById(R.id.pokemonNAME);
        mPokeT1 = itemView.findViewById(R.id.pokemonTYPE1);
        mPokeT2 = itemView.findViewById(R.id.pokemonTYPE2);
        mPokeImage = itemView.findViewById(R.id.pokemonIMAGE);
        recommendLayout = itemView.findViewById(R.id.recommendLayout);

    }

    public TextView getmPokeNO() {
        return mPokeNO;
    }

    public TextView getmPokeName() {
        return mPokeName;
    }

    public TextView getmPokeT1() {
        return mPokeT1;
    }

    public TextView getmPokeT2() {
        return mPokeT2;
    }

    public CircleImageView getmPokeImage() {
        return mPokeImage;
    }

    public RelativeLayout getRecommendLayout() {
        return recommendLayout;
    }
}
