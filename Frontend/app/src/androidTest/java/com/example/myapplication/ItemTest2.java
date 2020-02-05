package com.example.myapplication;

import com.example.myapplication.pokemonItemClassAndActivity.PokeItem;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemTest2 {

    private PokeItem pokemonItemTest = mock(PokeItem.class);

    @Test
    public void testName(){
        when(pokemonItemTest.getItemName()).thenReturn("Test");
        assertEquals(pokemonItemTest.getItemName(), "Test");
    }

    @Test
    public void testType1(){
        when(pokemonItemTest.getId()).thenReturn("1");
        assertEquals(pokemonItemTest.getId(), "1");
    }

    @Test
    public void testType2(){

        when(pokemonItemTest.getItemAbility()).thenReturn("grass");
        assertEquals(pokemonItemTest.getItemAbility(), "grass");
    }
}
