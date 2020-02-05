package com.example.myapplication;

import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.PokemonActivities.TeamSelector;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class onCreateOptionsMenuTest {
    private TeamSelector teamSelector = mock(TeamSelector.class);

    @Test
    public void testMenu() {
        Menu menuItem = mock(Menu.class);
        when(teamSelector.onCreateOptionsMenu(menuItem)).thenReturn(true);
        assertEquals(teamSelector.onCreateOptionsMenu(menuItem), true);
    }

    @Test
    public void testMenu2() {
        Menu menuItem = mock(Menu.class);
        when(teamSelector.onCreateOptionsMenu(menuItem)).thenReturn(false);
        assertEquals(teamSelector.onCreateOptionsMenu(menuItem), false);
    }
}
