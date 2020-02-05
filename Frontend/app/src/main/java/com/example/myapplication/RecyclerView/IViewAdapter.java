package com.example.myapplication.RecyclerView;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.RecyclerViewHolder.IViewHolder;

public abstract class IViewAdapter extends RecyclerView.Adapter<IViewHolder> {

    public void onBindViewHolder(@NonNull IViewHolder holder, int position){}

    public int getItemCount() {return 0;}
}
