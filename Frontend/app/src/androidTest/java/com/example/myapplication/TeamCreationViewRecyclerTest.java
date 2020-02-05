package com.example.myapplication;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.RecyclerView.TeamCreationViewAdapter;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TeamCreationViewRecyclerTest {
    private RecyclerView mockRecycler = mock(RecyclerView.class);
    private View mockView = mock(View.class);
    private TeamCreationViewAdapter mockAdapter = mock(TeamCreationViewAdapter.class);

    @Test
    public void getSizeTest() {
        when(mockAdapter.getItemCount()).thenReturn(1);

       assertEquals(mockAdapter.getItemCount(), 1);
    }
    @Test
    public void getAdapterTest() {
        when(mockRecycler.getAdapter()).thenReturn(mockAdapter);

        assertEquals(mockRecycler.getAdapter(), mockAdapter);
    }
    @Test
    public void testPosition(){
        when(mockRecycler.getChildAdapterPosition(mockView)).thenReturn(10);

        assertEquals(mockRecycler.getChildAdapterPosition(mockView), 10);
    }
}
