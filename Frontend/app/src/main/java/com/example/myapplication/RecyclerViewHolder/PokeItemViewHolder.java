package com.example.myapplication.RecyclerViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PokeItemViewHolder extends IViewHolder {

    public TextView itemName;
    public RelativeLayout itemlist;
    public CircleImageView image;

    public PokeItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.itemName);
        itemlist = itemView.findViewById(R.id.pokeItems);
        image = itemView.findViewById(R.id.imageItemPic);
    }

    public TextView getItemName() {
        return itemName;
    }

    public RelativeLayout getItemlist() {
        return itemlist;
    }

    public CircleImageView getImage() {
        return image;
    }
}
