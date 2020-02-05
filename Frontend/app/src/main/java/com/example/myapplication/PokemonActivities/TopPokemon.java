package com.example.myapplication.PokemonActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerView.TopPokemonViewAdapter;
import com.example.myapplication.app.AppController;

import com.example.myapplication.pokemonClass.Top100Pokemon;
import com.example.myapplication.user.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TopPokemon extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TopPokemonViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Top100Pokemon> pokemons = new ArrayList<>();
    private ArrayList<String> pokeRank = new ArrayList<>();

    private Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_pokemon);
        Intent intent = getIntent();
        user = new Users(intent.getStringExtra("userId"), intent.getStringExtra("userTeamId"));

        initImagePokemon();
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

    public void initImagePokemon(){
        String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/pokemon/top100";

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
                                int topCount = pokemon.getInt("topCount");
                                String imageTemp = pokemon.getString("image");
                                pokemons.add(new Top100Pokemon(name, firstType, secondType, id, imageTemp,
                                        ability1, ability2, hiddenAbility, hp, atk, def, spA, spD, speed, topCount));
                            }
                            updateData();

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

    private void updateData(){
        initSearchView();
    }

    private void initSearchView(){

        pokeRank = getRankNum(pokemons);
        mRecyclerView = findViewById(R.id.topPokmeonRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        mAdapter = new TopPokemonViewAdapter(pokemons, user, this, pokeRank);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    //Filter search
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dropdown, menu);
        inflater.inflate(R.menu.menu_searchpokemon, menu);

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

    public ArrayList<String> getRankNum(ArrayList<Top100Pokemon> list) {
        ArrayList<String> temp = new ArrayList<>();
        int rankNum = 1;
        temp.add(String.valueOf(rankNum));
        for (int i = 1; i < 100; i++) {
            rankNum++;
            temp.add(String.valueOf(rankNum));
        }
        return temp;
    }
}
