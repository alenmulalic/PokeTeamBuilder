package com.example.myapplication.PokemonActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import org.json.JSONException;
import org.json.JSONObject;

public class PokemonInfoPage extends AppCompatActivity {
    private TextView pokename, pokeno, type1, type2, ability1, ability2, hiddenAbility, hp, atk, def, spA, spD, speed;
    private String addordelete;
    private Button addpokemon;
    Users user;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info_page);
        intent = getIntent();
        setTextViews();
        Pokemon pokemon = makePokemonFromIntent(intent);
        user = getUserDataFromIntent(intent);
        setText(pokemon);
        setColor(pokemon);
        loadImages(pokemon);
        setonClickListeners(pokemon, user);
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
                intent.putExtra("User", user);
                intent.putExtra("result", "success");
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
    private void deleteFromTeam(Users user){
        Intent intent = new Intent (this, TeamSelector.class);
        intent.putExtra("User", user);
        intent.putExtra("id", user.getUserId());
        startActivity(intent);
    }
    private void addToTeam(Users user){
        Intent intent = new Intent(this, TeamCreation.class);
        intent.putExtra("User", user);
        intent.putExtra("id", user.getUserId());
        startActivity(intent);
    }
    private void deletePokemonFromTeam(Pokemon poke, Users user){
        final String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/user/" + user.getUserId() + "/team/deletePokemon/" +
                poke.getNumber() + "/" + user.getUserTeamId();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
            }
        });
        AppController.getInstance().addToRequestQueue(request);
        deleteFromTeam(user);
    }

    private void addtoPokemonTeam(Pokemon poke, Users user) {
        final String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/user/" + user.getUserId() + "/team/" + user.getUserTeamId() + "/pokemon";
        int pokeNumber = Integer.parseInt(poke.getNumber());

        JSONObject testPoke = new JSONObject();
        try {
            testPoke.put("id", pokeNumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, testPoke, new Response.Listener<JSONObject>() {
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
    private void setTextViews(){
        pokename = findViewById(R.id.nameInfobutton);
        pokeno = findViewById(R.id.noinfobutton);
        type1 = findViewById(R.id.type1infobutton);
        type2 = findViewById(R.id.type2infobutton);
        addpokemon = findViewById(R.id.addtoteambutton);
        ability1 = findViewById(R.id.ability1text);
        ability2 = findViewById(R.id.ability2text);
        hiddenAbility = findViewById(R.id.hiddenabilitytext);
        hp = findViewById(R.id.hptext);
        atk = findViewById(R.id.atktext);
        def = findViewById(R.id.deftext);
        spA = findViewById(R.id.spatext);
        spD = findViewById(R.id.spdtext);
        speed = findViewById(R.id.speedtext);
    }

    private void setonClickListeners(Pokemon poke, Users user){
        if(addordelete.equals("delete")){
            addpokemon.setText("Delete Pokemon");
            addpokemon.setOnClickListener((View v) ->{
                deletePokemonFromTeam(poke, user);

            });
        }else{
            addpokemon.setOnClickListener((View v) ->{
                addtoPokemonTeam(poke, user);
                addToTeam(user);

            });
        }
    }

    public Pokemon makePokemonFromIntent(Intent in){
        Pokemon pokemons = (Pokemon) in.getSerializableExtra("Pokemon");
        addordelete = in.getStringExtra("addOrDelete");
        return pokemons;
    }
    private Users getUserDataFromIntent(Intent in){
        Users users = (Users) in.getSerializableExtra("User");
        return users;
    }

    private void setText(Pokemon poke){
        //set text
        pokename.setText(poke.getName());
        pokeno.setText(poke.getNumber());
        type1.setText(poke.getType1());
        type2.setText(poke.getType2());
        ability1.setText(poke.getAbility1());
        ability2.setText(poke.getAbility2());
        hiddenAbility.setText(poke.getHiddenAbility());
        hp.setText(poke.getHp());
        atk.setText(poke.getAtk());
        def.setText(poke.getDef());
        spA.setText(poke.getSpA());
        spD.setText(poke.getSpD());
        speed.setText(poke.getSpeed());
    }
    private void setColor(Pokemon poke){
        String pokeType1Lower = poke.getType1().toLowerCase();
        String pokeType2Lower = poke.getType2().toLowerCase();
        type1.setBackgroundColor(poke.getTypeColor(pokeType1Lower, this));
        type2.setBackgroundColor(poke.getTypeColor(pokeType2Lower, this));
        if(pokeType1Lower.equals(pokeType2Lower)){
            type2.setText("");
            type2.setBackgroundColor(ContextCompat.getColor(this, R.color.pokeballRed));
        }
    }
    private void loadImages(Pokemon poke){
        Glide.with(this).load( poke.getImage())
                .into((ImageView) findViewById(R.id.imageInfo));
    }
}
