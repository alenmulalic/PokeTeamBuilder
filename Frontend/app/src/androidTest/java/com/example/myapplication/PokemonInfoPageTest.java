package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

import com.example.myapplication.PokemonActivities.PokemonInfoPage;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PokemonInfoPageTest {
    private Pokemon pokemon;
    @Test
    public void intentTests(){
        Intent intent = mock(Intent.class);
        intent.putExtra("name", "test");
        when(intent.getStringExtra("name")).thenReturn("testName");
        when(intent.getStringExtra("no")).thenReturn("testNo");
        when(intent.getStringExtra("type1")).thenReturn("testType1");
        when(intent.getStringExtra("type2")).thenReturn("testType2");

        assertEquals(intent.getStringExtra("name"), "testName");
        assertEquals(intent.getStringExtra("no"), "testNo");
        assertEquals(intent.getStringExtra("type1"), "testType1");
        assertEquals(intent.getStringExtra("type2"), "testType2");
    }
    @Test
    public void makePokemonFromIntentTest(){
        PokemonInfoPage test = mock(PokemonInfoPage.class);
        Context context = mock(Context.class);
        Intent intent = new Intent(context, PokemonInfoPage.class);
        Pokemon pokemon = new Pokemon("testName", "type1", "type2", "number", "image", "ability1", "ability2", "hiddenAbility", "hp", "atk", "def", "spA", "spD",
                "speed");
        Users user = new Users("2", "3");
        intent.putExtra("Pokemon", pokemon);
        intent.putExtra("User", user);

        Pokemon pokeTest = test.makePokemonFromIntent(intent);
        if(pokeTest != null) {
            assertEquals(pokeTest.getName(), "testName");
            assertEquals(pokeTest.getType1(), "type1");
            assertEquals(pokeTest.getType2(), "type2");
            assertEquals(pokeTest.getNumber(), "number");
            assertEquals(pokeTest.getImage(), "image");
            assertEquals(pokeTest.getAbility1(), "ability1");
            assertEquals(pokeTest.getAbility2(), "ability2");
            assertEquals(pokeTest.getHiddenAbility(), "hiddenAbility");
            assertEquals(pokeTest.getHp(), "hp");
            assertEquals(pokeTest.getAtk(), "atk");
            assertEquals(pokeTest.getDef(), "def");
            assertEquals(pokeTest.getSpD(), "spD");
            assertEquals(pokeTest.getSpA(), "spA");
            assertEquals(pokeTest.getSpeed(), "speed");
        }
    }
}
