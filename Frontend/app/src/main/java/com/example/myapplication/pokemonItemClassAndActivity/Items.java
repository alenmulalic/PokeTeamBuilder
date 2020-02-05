package com.example.myapplication.pokemonItemClassAndActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerView.PokeItemsViewAdapter;
import com.example.myapplication.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Items extends AppCompatActivity {
    private ArrayList<PokeItem> pokeItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        initImagePokemon();
    }
    public void initImagePokemon(){

        String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/items";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newArray = response.getJSONArray("Items");
                            for(int i = 0; i < newArray.length(); i++){
                                JSONObject item = newArray.getJSONObject(i);
                                String id = item.getString("itemId");
                                String itemName = item.getString("itemName");
                                String itemAbility = item.getString("itemAbility");
                                String image = item.getString("itemImage");
                                PokeItem pokeItem = new PokeItem(id, itemName, itemAbility, image);
                                pokeItems.add(pokeItem);
                            }
                            initSearchView();

                        } catch (JSONException e) {
                            System.out.print("failed");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
                System.out.println("Fail");
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    private void initSearchView(){

        RecyclerView recyclerView = findViewById(R.id.itemCreation);
        PokeItemsViewAdapter adapter = new PokeItemsViewAdapter(pokeItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
