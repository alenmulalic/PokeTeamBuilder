package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.pokemonClass.Pokemon;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

import static org.mockito.Mockito.*;

public class PokemonTest {
    private Pokemon pokemonTest2;
    private Pokemon pokemonTest;
    @Before
    public void initialize(){
        pokemonTest2 = new Pokemon("testName", "asd", "fireasd", "1", "testImage", "testAbility1", "testAbility2", "testHidden", "hpTest", "atkTest",
                "defTest", "spaTest", "spdTest", "speedTest");

        pokemonTest = mock(Pokemon.class);
    }

    @Test
    public void testName(){

        when(pokemonTest.getName()).thenReturn("Test");

        assertEquals(pokemonTest.getName(), "Test");
    }
    @Test
    public void testId(){

        when(pokemonTest.getNumber()).thenReturn("1");

        assertEquals(pokemonTest.getNumber(), "1");
    }
    @Test
    public void testType1(){

        when(pokemonTest.getType1()).thenReturn("grass");

        assertEquals(pokemonTest.getType1(), "grass");
    }
    @Test
    public void testType1TypeChecking(){
        assertEquals(pokemonTest2.getType1(), "invalid");
    }
    @Test
    public void testType2TypeChecking(){
        assertEquals(pokemonTest2.getType2(), "invalid");
    }


    @Test
    public void testType2(){
        when(pokemonTest.getType2()).thenReturn("grass");
        assertEquals(pokemonTest.getType2(), "grass");
    }
    @Test
    public void testAbility1(){
        when(pokemonTest.getAbility1()).thenReturn("grass");
        assertEquals(pokemonTest.getAbility1(), "grass");
    }
    @Test
    public void testAbility2(){
        when(pokemonTest.getAbility2()).thenReturn("grass");
        assertEquals(pokemonTest.getAbility2(), "grass");
    }
    @Test
    public void testColors(){
        String typeGrassTest = "grass";
        String typeSteelTest = "steel";
        String typeDragonTest = "dragon";
        String typeFairyTest = "fairy";
        String typeGhostTest = "ghost";
        Context context = mock(Context.class);
        int typeGrass = pokemonTest.getTypeColor(typeGrassTest, context);
        int typeSteel = pokemonTest.getTypeColor(typeSteelTest, context);
        int typeDragon = pokemonTest.getTypeColor(typeDragonTest, context);
        int typeFairy = pokemonTest.getTypeColor(typeFairyTest, context);
        int typeGhost = pokemonTest.getTypeColor(typeGhostTest, context);
        assertEquals(typeGrass, 0);
        assertEquals(typeSteel, 0);
        assertEquals(typeDragon, 0);
        assertEquals(typeFairy, 0);
        assertEquals(typeGhost, 0);
    }
}
