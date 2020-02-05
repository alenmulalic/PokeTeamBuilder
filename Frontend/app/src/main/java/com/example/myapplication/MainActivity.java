package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.FriendsAndChat.AllFriends;
import com.example.myapplication.FriendsAndChat.Chat;
import com.example.myapplication.FriendsAndChat.MyFriends;
import com.example.myapplication.PokemonActivities.TeamCreation;
import com.example.myapplication.PokemonActivities.TeamSelector;
import com.example.myapplication.PokemonActivities.TopPokemon;
import com.example.myapplication.login.Login;
import com.example.myapplication.pokemonItemClassAndActivity.Items;

public class MainActivity extends AppCompatActivity {

    private Button teamSelector;
    private Button teamCreation;
    private Button friends;
    private Button item;
    private Button topPokemonButton;
    private Button chat;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent thisIntent = getIntent();

        if(thisIntent.getStringExtra("result") == null){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
        id = thisIntent.getStringExtra("id");

        //set Ids
        item = findViewById(R.id.itemButton);
        teamSelector = findViewById(R.id.teamSelector);
        teamCreation = findViewById(R.id.teamCreation);
        topPokemonButton = findViewById(R.id.topPokemonButton);
        chat = findViewById(R.id.chat);
        friends = findViewById(R.id.friends);

        setOnClickListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dropdown, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuItem1:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("result", "success");
                intent.putExtra("id", id);
                startActivity(intent);
                return true;
            case R.id.menuItem2:
                Intent intent1 = new Intent(this, TeamCreation.class);
                startActivity(intent1);
                return true;
            case R.id.menuItem3:
                Intent intent2 = new Intent(this, TeamSelector.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setOnClickListeners(){
        item.setOnClickListener((View v) -> {
            openItemActivity();
        });

        teamSelector.setOnClickListener((View v) -> {
            openTeamSelectorActivity();
        });

        teamCreation.setOnClickListener((View v) -> {
            openTeamCreationActivity();
        });

        friends.setOnClickListener((View v) -> {
            openFriendsActivity();
        });

        topPokemonButton.setOnClickListener((View v) -> {
            openTopPokemonActivity();
        });

        chat.setOnClickListener((View v) -> {
            openChatActivity();
        });
    }

    public void openTeamCreationActivity(){
        Intent intent = new Intent(this, TeamCreation.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    public void openTeamSelectorActivity(){
        Intent intent = new Intent(this, TeamSelector.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    public void openItemActivity() {
        Intent intent = new Intent(this, Items.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    public void openChatActivity(){
        Intent intent = new Intent(this, Chat.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    public void openFriendsActivity(){
        Intent intent = new Intent(this, MyFriends.class);
        intent.putExtra("userId", id);
        startActivity(intent);
    }
    public void openTopPokemonActivity() {
        Intent intent = new Intent(this, TopPokemon.class);
        intent.putExtra("userId", id);
        startActivity(intent);
    }
}
