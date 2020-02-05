package com.example.myapplication;

import com.example.myapplication.pokemonItemClassAndActivity.PokeItem;
import com.example.myapplication.user.Users;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersTest {
    private Users user = mock(Users.class);

    @Test
    public void testUserId(){
        when(user.getUserId()).thenReturn("1");
        assertEquals(user.getUserId(), "1");
    }

    @Test
    public void testUserTeamId(){
        when(user.getUserTeamId()).thenReturn("1");
        assertEquals(user.getUserTeamId(), "1");
    }
}
