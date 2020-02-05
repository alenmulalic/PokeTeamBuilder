package com.example.myapplication;

import com.example.myapplication.FriendsAndChat.Friend;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendTest {
    private Friend pokemonItemTest = mock(Friend.class);

    @Test
    public void testName(){
        when(pokemonItemTest.getFriendID()).thenReturn("Test");
        assertEquals(pokemonItemTest.getFriendID(), "Test");
    }

    @Test
    public void testType1(){
        when(pokemonItemTest.getFriendName()).thenReturn("1");
        assertEquals(pokemonItemTest.getFriendName(), "1");
    }
    @Test
    public void testType2(){
        when(pokemonItemTest.getFriendName()).thenReturn("grass");
        assertEquals(pokemonItemTest.getFriendName(), "grass");
    }
}
