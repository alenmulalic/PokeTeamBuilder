package com.example.myapplication.PokemonActivities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerView.TeamSelectionViewAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamSelector extends AppCompatActivity {
    private static final String TAG = "TeamSelector";
    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private Spinner spinner;
    private String spinnerData = "";
    private Button spinnerButton;
    private Users user;
    private Intent thisIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selector);
        thisIntent = getIntent();
        Users user = (Users) thisIntent.getSerializableExtra("User");
        if(user != null){
            renderTeam(user);
        }
        setSpinner();
    }
    private void renderTeam(Users user){
        String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/user/" +user.getUserId() + "/team/" + user.getUserTeamId();
        initImagePokemon(URL);
    }

    //Dropdown for teams
    private void setSpinner(){
        spinnerButton = findViewById(R.id.teamselectorButton);
        spinner = findViewById(R.id.tempList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinList));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinnerButton.setOnClickListener((View v)->{
            renderSelectedTeam();
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dropdown, menu);
        return true;
    }

    // Menu options to go to other activities
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuItem1:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("result", "success");
                intent.putExtra("id", thisIntent.getStringExtra("id"));
                startActivity(intent);
                return true;
            case R.id.menuItem2:
                Intent intent1 = new Intent(this, TeamCreation.class);
                intent1.putExtra("id", thisIntent.getStringExtra("id"));
                startActivity(intent1);
                return true;
            case R.id.menuItem3:
                Intent intent2 = new Intent(this, TeamSelector.class);
                intent2.putExtra("id", thisIntent.getStringExtra("id"));
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void renderSelectedTeam(){
        spinnerData = spinner.getSelectedItem().toString();
        if(spinnerData.equals("Team 1")){
            spinnerData = "1";
        }else if(spinnerData.equals("Team 2")){
            spinnerData = "2";
        }else{
            spinnerData = "3";
        }
        user = new Users(thisIntent.getStringExtra("id"), spinnerData);
        String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/user/" +user.getUserId() + "/team/" + user.getUserTeamId();
        pokemons.clear();
        initImagePokemon(URL);
    }

    public void initImagePokemon(String URL){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newArray = response.getJSONArray("PokeTeam");
                            for(int i = 0; i < newArray.length(); i++){
                                JSONObject pokemon = newArray.getJSONObject(i);
                                String id = pokemon.getString("pokeDex");
                                String firstType = pokemon.getString("firstPokeType");
                                String name = pokemon.getString("pokeName");
                                String secondType = pokemon.getString("secondaryPokeType");
                                String ability1 = pokemon.getString("ability1");
                                String ability2 = pokemon.getString("ability2");
                                String hiddenAbility = pokemon.getString("hiddenAbility");
                                String hp = pokemon.getString("hp");
                                String atk = pokemon.getString("atk");
                                String def = pokemon.getString("def");
                                String spA = pokemon.getString("spA");
                                String spD = pokemon.getString("spD");
                                String speed = pokemon.getString("spe");
                                String imageTemp = pokemon.getString("image");//
                                pokemons.add(new Pokemon(name, firstType, secondType, id, imageTemp,
                                        ability1, ability2, hiddenAbility, hp, atk, def, spA, spD, speed));

                            }
                            initPokeArray();
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
    private void initPokeArray(){
        spinner.setVisibility(View.VISIBLE);
        spinnerButton.setVisibility(View.VISIBLE);
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init Recycler view");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TeamSelectionViewAdapter adapter = new TeamSelectionViewAdapter(pokemons, user, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
