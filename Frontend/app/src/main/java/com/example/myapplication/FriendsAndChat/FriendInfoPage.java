package com.example.myapplication.FriendsAndChat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;
import com.example.myapplication.user.Users;

import org.json.JSONException;
import org.json.JSONObject;

public class FriendInfoPage extends AppCompatActivity {
    TextView friendId;
    TextView friendName;
    Button addButton, spinnerButton;
    Spinner spinner;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_info_page);
        Intent intent = getIntent();
        Friend friend = (Friend) intent.getSerializableExtra("Friend");
        Users user = (Users) intent.getSerializableExtra("User");
        friendId = findViewById(R.id.friendInfoId);
        friendName = findViewById(R.id.friendInfoName);
        addButton = findViewById(R.id.friendButton);
        friendId.setText(friend.getFriendID());
        friendName.setText(friend.getFriendName());
        setSpinner(friend);



        addButton.setOnClickListener((View v)-> {
            addtoFriend(friend, user);
            returntofriend(user);
        });
    }
    private void setSpinner(Friend friend){
        spinner = findViewById(R.id.friendspinnerCreation);
        spinnerButton = findViewById(R.id.friendSpinnerButton);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinList));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinnerButton.setOnClickListener((View v) ->{
            renderSelectedTeam(friend);
        });
    }
    private void renderSelectedTeam(Friend friend){
        String spinnerData = spinner.getSelectedItem().toString();
        if(spinnerData.equals("Team 1")){
            spinnerData = "1";
        }else if(spinnerData.equals("Team 2")){
            spinnerData = "2";
        }else if (spinnerData.equals("Team 3")){
            spinnerData = "3";
        }
        Users user = new Users(friend.getFriendID(), spinnerData);
        Intent intent = new Intent(this, FriendsPokemon.class);
        intent.putExtra("User", user);
        if(!spinnerData.equals("Select Team")) {
            startActivity(intent);
        }
    }
    private void addtoFriend(Friend friend, Users user) {
        final String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/user/" + user.getUserId() + "/addfriend/" + friend.getFriendID();
        int pokeNumber = Integer.parseInt(friend.getFriendID());

        JSONObject testPoke = new JSONObject();
        try {
            testPoke.put("id", pokeNumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject objres = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    private void returntofriend(Users user){
        Intent intent = new Intent(this, AllFriends.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

}
