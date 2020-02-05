package com.example.myapplication.PokemonActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
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
import com.example.myapplication.RecyclerView.SearchViewAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchPokemon extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private SearchViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private Users user;

    private Spinner spinner;
    private Button spinnerButton;
    private String spinnerData = "";
    private String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pokemon);
        Intent intent = getIntent();
        user = new Users(intent.getStringExtra("userId"), intent.getStringExtra("userTeamId"));
        setSpinner();
//        initImagePokemon();
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
        spinner = findViewById(R.id.spinnerTier);
        spinnerButton = findViewById(R.id.TierSpinnerButton);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinTierList));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinnerButton.setOnClickListener((View v) ->{
            renderTier();
        });
    }

    private void renderTier(){
        spinnerData = spinner.getSelectedItem().toString();
        String[] tierNames = new String[]{"All Pokemon", "Over Used", "Under Used", "Rarely Used", "Never Used", "Uber", "Rarely Used Banlist",
                "Never Used Banlist", "Tier list PU", "Tier list PU Banlist", "Not Fully Evolved", "Little Cup", "Little Cup Uber"};
        String[] tierDesignators = new String[] {"", "ou", "uu", "ru", "nu", "uber", "rubl", "nubl", "pu", "publ", "nfe", "lc", "lcuber"};
        String tierValue = "";
        for(int i = 0; i < tierNames.length; i++){
            if(spinnerData.equals(tierNames[i])){
                tierValue = tierDesignators[i];
            }
        }
        URL = "http://coms-309-sb-1.misc.iastate.edu:8080/pokemons/"  + tierValue;
        pokemons.clear();
        initImagePokemon();
    }

    public void initImagePokemon(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newArray = response.getJSONArray("Pokemon");
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
                                int numberId = Integer.parseInt(id);
                                String imageTemp = pokemon.getString("image");
                                pokemons.add(new Pokemon(name, firstType, secondType, id, imageTemp,
                                        ability1, ability2, hiddenAbility, hp, atk, def, spA, spD, speed));
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

    private void initSearchView(){
        mRecyclerView = findViewById(R.id.searchRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SearchViewAdapter(pokemons, user, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    //Filter Search
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
}