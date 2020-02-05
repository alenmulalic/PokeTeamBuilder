package com.example.myapplication.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.FriendsAndChat.Friend;
import com.example.myapplication.FriendsAndChat.FriendInfoPage;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewHolder.AllFriendsViewHolder;
import com.example.myapplication.user.Users;

import java.util.ArrayList;

public class AllFriendsViewAdapter extends RecyclerView.Adapter<AllFriendsViewHolder> implements Filterable {
    private ArrayList<Friend> alluser;
    private ArrayList<Friend> alluserFilted;
    private Context mContext;
    private Users user;

    public AllFriendsViewAdapter(ArrayList<Friend> allUserList, Users user, Context context)
    {
        alluser = allUserList;
        alluserFilted = new ArrayList<>(allUserList);
        this.user = user;
        mContext = context;
    }

    @NonNull
    @Override
    public AllFriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_allfriends, parent, false);
        AllFriendsViewHolder afvh = new AllFriendsViewHolder(view);
        return afvh;
    }

    //Setting text and images
    @Override
    public void onBindViewHolder(@NonNull AllFriendsViewHolder holder, int position)
    {
        final Friend currentUser = alluser.get(position);
        holder.userID.setText(currentUser.getFriendID());
        holder.userID.setTextColor(Color.WHITE);
        holder.userName.setText(currentUser.getFriendName());
        holder.userName.setTextColor(Color.WHITE);

        holder.allFriendLayout.setOnClickListener((View v) -> {
                Intent intent = new Intent(mContext, FriendInfoPage.class);
                intent.putExtra("Friend", alluser.get(position));
                intent.putExtra("User", user);
                v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount()
    {
        return alluser.size();
    }

    // Creating the filter based on the user input
    @Override
    public Filter getFilter() {
        return userFilter;
    }

    private Filter userFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Friend> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() < 0)
            {
                filteredList.addAll(alluserFilted);
            }
            else
            {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Friend friend : alluserFilted)
                {
                    if(friend.getFriendName().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(friend);
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
            alluser.clear();
            alluser.addAll((ArrayList<Friend>)filterResults.values);
            notifyDataSetChanged();
        }
    };

}
