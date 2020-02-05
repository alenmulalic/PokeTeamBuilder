package com.example.myapplication.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewHolder.IViewHolder;
import com.example.myapplication.RecyclerViewHolder.PokeItemViewHolder;
import com.example.myapplication.pokemonItemClassAndActivity.ItemInfoPage;
import com.example.myapplication.pokemonItemClassAndActivity.PokeItem;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.myapplication.app.AppController.TAG;

public class PokeItemsViewAdapter extends RecyclerView.Adapter<PokeItemViewHolder>
{
    private ArrayList<PokeItem> items;
    private Context mContext;

    public PokeItemsViewAdapter(ArrayList<PokeItem> items, Context mContext)
    {
        super();
        this.items = items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PokeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pokeitems, parent, false);
        PokeItemViewHolder holder = new PokeItemViewHolder(view);
        return holder;
    }

    //Setting text and images
    @Override
    public void onBindViewHolder(@NonNull PokeItemViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");
        Glide.with(mContext)
                .asBitmap()
                .load(items.get(position).getImage())
                .into(holder.image);

        holder.itemName.setText(items.get(position).getItemName());
        holder.itemlist.setOnClickListener((View v) ->{
             Intent intent = new Intent(mContext, ItemInfoPage.class);
             intent.putExtra("Item", items.get(position));

             v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
