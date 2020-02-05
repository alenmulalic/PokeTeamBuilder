package com.example.myapplication.FriendsAndChat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.PokemonActivities.SearchPokemon;
import com.example.myapplication.PokemonActivities.TeamCreation;
import com.example.myapplication.PokemonActivities.TeamSelector;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerView.AllFriendsViewAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.user.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyFriends extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AllFriendsViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Friend> usersFriends = new ArrayList<>();
    private Users user;
    String userId;
    private Button findBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);
        Intent intent = getIntent();
        user = new Users(intent.getStringExtra("userId"), "0");
        if(user != null){
            renderFriends(user);
        }
        userId = intent.getStringExtra("userId");
        findBTN = findViewById(R.id.findButton);
        setOnClickListeners();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuItem1:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("result", "success");
                intent.putExtra("id",user.getUserId());
                startActivity(intent);
                return true;
            case R.id.menuItem2:
                Intent intent1 = new Intent(this, TeamCreation.class);
                intent1.putExtra("id", user.getUserId());
                startActivity(intent1);
                return true;
            case R.id.menuItem3:
                Intent intent2 = new Intent(this, TeamSelector.class);
                intent2.putExtra("id", user.getUserId());
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void renderFriends(Users user){
        String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/user/" +user.getUserId() + "/friendlist/";
        initImagePokemon(URL);
    }

    public void initImagePokemon(String url){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newArray = response.getJSONArray("id");
                            for(int i = 0; i < newArray.length(); i++){
                                JSONObject Users = newArray.getJSONObject(i);
                                String userID = Users.getString("userId");
                                String userName = Users.getString("userName");

                                usersFriends.add(new Friend(userID, userName));
                            }
//                            updateData();
                            initSearchView();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    private void initSearchView(){
        mRecyclerView = findViewById(R.id.myfriendsRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AllFriendsViewAdapter(usersFriends, user, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_searchpokemon, menu);
        inflater.inflate(R.menu.menu_dropdown, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    public void setOnClickListeners(){
        findBTN.setOnClickListener((View v) -> {
            openAllFriendsActivity();
        });
    }

    public void openAllFriendsActivity(){
        Intent intent = new Intent(this, AllFriends.class);
        startActivity(intent);
    }

    private void findallFriends(){
        Intent intent = new Intent(this, AllFriends.class);
        intent.putExtra("User", user);
        intent.putExtra("userId", user.getUserId());
        intent.putExtra("userTeamId", user.getUserTeamId());
        startActivity(intent);
    }
}
