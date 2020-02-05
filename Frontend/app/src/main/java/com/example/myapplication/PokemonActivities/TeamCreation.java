package com.example.myapplication.PokemonActivities;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.myapplication.RecyclerView.TeamCreationViewAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamCreation extends AppCompatActivity {
    private ArrayList<Pokemon> pokemon = new ArrayList<>();
    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private Users user;
    private Button condBtn;
    private Spinner spinner;
    private Button spinnerButton;
    private String spinnerData = "";
    private Intent thisIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_creation);
        thisIntent = getIntent();
        Users user = (Users) thisIntent.getSerializableExtra("User");
        if(user != null){
            renderTeam(user);
        }
        setSpinner();
        condBtn = findViewById(R.id.conditionalButton);
        condBtn.setVisibility(View.VISIBLE);
        condBtn.setText("Search Pokemon");
        condBtn.setOnClickListener((View v) ->{
            setCondBtn();
        });
    }
    private void setCondBtn(){
        Intent intent = new Intent(this, SearchPokemon.class);
        Users user = new Users(thisIntent.getStringExtra("id"), "0");
        intent.putExtra("User", user);
        startActivity(intent);
    }


    //Dropdown for teams
    private void setSpinner(){
        spinner = findViewById(R.id.spinnerCreation);
        spinnerButton = findViewById(R.id.teamCreationSpinnerButton);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinList));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinnerButton.setOnClickListener((View v) ->{
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
                intent.putExtra("id",thisIntent.getStringExtra("id"));
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
    private void renderTeam(Users user){
        String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/user/" +user.getUserId() + "/team/" + user.getUserTeamId();
        initImagePokemon(URL);
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
                                String id = pokemon.getString("id");
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
                                String imageTemp = pokemon.getString("image");
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

    private void initPokeArray (){
        if(pokemons.size() == 0 || pokemons == null){
            condBtn.setVisibility(View.VISIBLE);
            condBtn.setText("Search Pokemon");
            condBtn.setOnClickListener((View v) ->{
                searchPokeList();
            });

        }else if(pokemons.size() >= 1 ){
            condBtn.setVisibility(View.VISIBLE);
            condBtn.setText("Recommended Pokemon");

            condBtn.setOnClickListener((View v) -> {
                    recommendedPokeList();
            });
        }
        spinner.setVisibility(View.VISIBLE);
        spinnerButton.setVisibility(View.VISIBLE);
        initRecyclerView();
    }
    private void searchPokeList(){
        Intent intent = new Intent(this, SearchPokemon.class);
        intent.putExtra("User", user);
        intent.putExtra("userId", user.getUserId());
        intent.putExtra("userTeamId", user.getUserTeamId());
        startActivity(intent);
    }
    private void recommendedPokeList(){
        Intent intent = new Intent(this, RecommendPokemon.class);
        intent.putExtra("User", user);
        intent.putExtra("userId", user.getUserId());
        intent.putExtra("userTeamId", user.getUserTeamId());
        startActivity(intent);
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.teamCreation);
        TeamCreationViewAdapter adapter = new TeamCreationViewAdapter(pokemons, user, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
