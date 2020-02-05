package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.RecyclerView.SearchViewAdapter;
import com.example.myapplication.pokemonClass.Pokemon;
import com.example.myapplication.user.Users;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.logging.Filter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchTest
{
    private SearchViewAdapter adapterTest = mock(SearchViewAdapter.class);
    private ArrayList<Pokemon> pokemonList;
    private Context context = null;
    private Filter filter;
    private Users users;
    @Before
    public void setup()    {
        adapterTest = new SearchViewAdapter(pokemonList, users, context);
    }


    @Test
    public void filterTest()    {
        when(adapterTest.getFilter()).thenReturn((android.widget.Filter) filter);
        assertEquals(filter, adapterTest.getFilter());
        verify(adapterTest.getFilter());
    }

    @Test
    public void itemCountTest()
    {
        when(adapterTest.getItemCount()).thenReturn(1);
        assertEquals(1, adapterTest.getItemCount());
        verify(adapterTest.getItemCount());
    }
}
