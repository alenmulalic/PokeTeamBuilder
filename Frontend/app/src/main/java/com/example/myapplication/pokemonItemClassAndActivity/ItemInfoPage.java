package com.example.myapplication.pokemonItemClassAndActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.user.Users;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemInfoPage extends AppCompatActivity {
    private TextView itemId, itemName, itemAbility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info_page);
        PokeItem items = getItemData(getIntent());
        setTextViews();
        setText(items);
        loadImages(items);
    }

    private PokeItem getItemData(Intent in){
        PokeItem items = (PokeItem) in.getSerializableExtra("Item");
        return items;
    }
    private void setTextViews(){
        itemId = findViewById(R.id.itemIdText);
        itemName = findViewById(R.id.itemNameText);
        itemAbility = findViewById(R.id.itemAbilityText);
    }
    private void setText(PokeItem items){
        itemId.setText(items.getId());
        itemName.setText(items.getItemName());
        itemAbility.setText(items.getItemAbility());
    }
    private void loadImages(PokeItem item){
            Glide.with(this).load( item.getImage())
                    .into((ImageView) findViewById(R.id.itemImage));
}       }
