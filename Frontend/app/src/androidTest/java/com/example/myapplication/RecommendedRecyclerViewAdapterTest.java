package com.example.myapplication;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.RecyclerView.RecommendViewAdapter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecommendedRecyclerViewAdapterTest {
    private RecyclerView mockRecycler = mock(RecyclerView.class);
    private View mockView = mock(View.class);
    private RecommendViewAdapter mockAdapter = mock(RecommendViewAdapter.class);
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
