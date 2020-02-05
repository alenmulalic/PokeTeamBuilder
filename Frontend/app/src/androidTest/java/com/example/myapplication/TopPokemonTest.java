package com.example.myapplication;

import com.example.myapplication.PokemonActivities.TopPokemon;
import com.example.myapplication.pokemonClass.Top100Pokemon;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TopPokemonTest {
    private TopPokemon topPokemonTest = mock(TopPokemon.class);
    private Top100Pokemon top100Pokemon = mock(Top100Pokemon.class);
    private ArrayList<Top100Pokemon> topPokemans = new ArrayList<>();

    @Before
    public void intilizae(){
        Top100Pokemon pokemons = new Top100Pokemon("topPokemon", "type1", "type2", "1", "image",
                "ability1", "ability2", "hiddenAbility", "hp", "atk", "def", "spA","spD",
                "speed", 50);
        Top100Pokemon pokemons2 = new Top100Pokemon("topPokemon2", "type1", "type2", "1", "image",
                "ability1", "ability2", "hiddenAbility", "hp", "atk", "def", "spA","spD",
                "speed", 30);
        Top100Pokemon pokemons3 = new Top100Pokemon("topPokemon3", "type1", "type2", "1", "image",
                "ability1", "ability2", "hiddenAbility", "hp", "atk", "def", "spA","spD",
                "speed", 20);
        Top100Pokemon pokemons4 = new Top100Pokemon("topPokemon", "type1", "type2", "1", "image",
                "ability1", "ability2", "hiddenAbility", "hp", "atk", "def", "spA","spD",
                "speed", 10);

        topPokemans.add(pokemons);
        topPokemans.add(pokemons2);
        topPokemans.add(pokemons3);
        topPokemans.add(pokemons4);
    }

    @Test
    public void testGetTopCount(){
        when(top100Pokemon.getTopCount()).thenReturn(1);
        assertEquals(top100Pokemon.getTopCount(), 1);
    }
    @Test
    public void testGetTopCount2(){
        assertEquals(topPokemans.get(0).getTopCount(), 50);
        assertEquals(topPokemans.get(1).getTopCount(), 30);
        assertEquals(topPokemans.get(2).getTopCount(), 20);
        assertEquals(topPokemans.get(3).getTopCount(), 10);
    }

    @Test
    public void testType2(){
        ArrayList<String> listOfTops = new ArrayList<>();
        listOfTops = topPokemonTest.getRankNum(topPokemans);
        if(listOfTops.size() != 0){
            assertEquals(listOfTops.get(0), "50");
            assertEquals(listOfTops.get(1), "30");
            assertEquals(listOfTops.get(2), "20");
            assertEquals(listOfTops.get(3), "10");
        }
    }
}
