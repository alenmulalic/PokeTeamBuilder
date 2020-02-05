package com.example.myapplication.RecyclerViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

public class AllFriendsViewHolder extends IViewHolder {

    public TextView userID;
    public TextView userName;
    public RelativeLayout allFriendLayout;

    public AllFriendsViewHolder(@NonNull View itemView) {
        super(itemView);
        userID = itemView.findViewById(R.id.userID);
        userName = itemView.findViewById(R.id.userName);
        allFriendLayout = itemView.findViewById(R.id.allFriendsLayout);
    }

    public TextView getUserID() {
        return userID;
    }

    public TextView getUserName() {
        return userName;
    }

    public RelativeLayout getAllFriendLayout() {
        return allFriendLayout;
    }
}
