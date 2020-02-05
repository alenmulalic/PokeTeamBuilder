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
import com.example.myapplication.RecyclerView.RecommendViewAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecommendPokemon extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private RecommendViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Users user;
    private Intent intent;
    private Spinner spinner;
    private Button spinnerButton;
    private String spinnerData;

    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_pokemon);
        intent = getIntent();
        user = new Users(intent.getStringExtra("userId"), intent.getStringExtra("userTeamId"));
        setSpinner();
        setmRecyclerView();
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

    // A dropdown for selecting tier
    private void setSpinner(){
        spinner = findViewById(R.id.tierSpinnerRecommended);
        spinnerButton = findViewById(R.id.recommendedSpinnerButton);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinTierList));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinnerButton.setOnClickListener((View v) -> {
            renderTier();
        });
    }

    private void setmRecyclerView(){
        mRecyclerView = findViewById(R.id.recommendRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecommendViewAdapter(pokemons,user, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void renderTier(){
        spinnerData = spinner.getSelectedItem().toString();
        String[] tierNames = new String[]{"All Pokemon", "Over Used", "Under Used", "Rarely Used", "Never Used", "Uber", "Rarely Used Banlist",
        "Never Used Banlist", "Tier list PU", "Tier list PU Banlist", "Not Fully Evolved", "Little Cup", "Little Cup Uber"};
        String[] tierDesignators = new String[] {"ag", "ou", "uu", "ru", "nu", "uber", "rubl", "nubl", "pu", "publ", "nfe", "lc", "lcuber"};
        String tierValue = "";
        for(int i = 0; i < tierNames.length; i++){
            if(spinnerData.equals(tierNames[i])){
                tierValue = tierDesignators[i];
            }
        }
        String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/pokemons/nextBest/"  +tierValue + "/" + user.getUserId() + "/" + user.getUserTeamId(); ;
        pokemons.clear();
        requestRecommendedPokemon(URL);
    }

    public void requestRecommendedPokemon(String URL)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newArray = response.getJSONArray("Pokemon");
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
                                int numberId = Integer.parseInt(id);
                                String imageTemp = pokemon.getString("image");
                                pokemons.add(new Pokemon(name, firstType, secondType, id, imageTemp,
                                        ability1, ability2, hiddenAbility, hp, atk, def, spA, spD, speed));
                            }
                            initRecommendView();

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
    private String setImage(int numberId){
        String imageTemp;
        if(numberId < 10){
            imageTemp = "https://www.serebii.net/sunmoon/pokemon/00" + numberId + ".png";
        }else if(numberId < 100){
            imageTemp = "https://www.serebii.net/sunmoon/pokemon/0" + numberId + ".png";
        }else{
            imageTemp = "https://www.serebii.net/sunmoon/pokemon/" + numberId + ".png";
        }
        return imageTemp;
    }

    private void initRecommendView()
    {
        RecyclerView recyclerView = findViewById(R.id.recommendRV);
        mAdapter = new RecommendViewAdapter(pokemons, user, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
