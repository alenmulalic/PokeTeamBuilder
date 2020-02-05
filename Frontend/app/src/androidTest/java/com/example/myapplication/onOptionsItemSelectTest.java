package com.example.myapplication;

import android.view.MenuItem;

import com.example.myapplication.PokemonActivities.TeamSelector;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class onOptionsItemSelectTest {
    private TeamSelector teamSelector = mock(TeamSelector.class);

    @Test
    public void testMenu() {
        MenuItem menuItem2 = mock(MenuItem.class);
        when(teamSelector.onOptionsItemSelected(menuItem2)).thenReturn(true);
        assertEquals(teamSelector.onOptionsItemSelected(menuItem2), true);
    }

    @Test
    public void testMenu2() {
        MenuItem menuItem2 = mock(MenuItem.class);
        when(teamSelector.onOptionsItemSelected(menuItem2)).thenReturn(false);
        assertEquals(teamSelector.onOptionsItemSelected(menuItem2), false);
    }
}
