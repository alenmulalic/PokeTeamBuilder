package com.example.myapplication;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.RecyclerViewHolder.RecommendViewHolder;

import org.junit.Before;
import org.junit.Test;

import de.hdodenhof.circleimageview.CircleImageView;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecomendViewHolderTest {
    private RecommendViewHolder rvh = mock(RecommendViewHolder.class);
    private View view = mock(View.class);
    private TextView textView = mock(TextView.class);
    private CircleImageView circleImageView = mock(CircleImageView.class);
    private RelativeLayout relativeLayout = mock(RelativeLayout.class);

    @Before
    public void intilizae() {

        rvh = new RecommendViewHolder(view);
    }

    @Test
    public void pokeNOTest() {
        when(rvh.getmPokeNO()).thenReturn(textView);
        assertEquals(rvh.getmPokeNO(), null);
    }

    @Test
    public void pokeNameTest() {
        when(rvh.getmPokeName()).thenReturn(textView);
        assertEquals(rvh.getmPokeName(), null);
    }

    @Test
    public void pokeType1Test() {
        when(rvh.getmPokeT1()).thenReturn(textView);
        assertEquals(rvh.getmPokeT1(), null);
    }

    @Test
    public void pokeType2Test() {
        when(rvh.getmPokeT2()).thenReturn(textView);
        assertEquals(rvh.getmPokeT2(), null);
    }

    @Test
    public void pokeImageTest() {
        when(rvh.getmPokeImage()).thenReturn(circleImageView);
        assertEquals(rvh.getmPokeImage(), null);
    }

    @Test
    public void pokeLayoutTest() {
        when(rvh.getRecommendLayout()).thenReturn(relativeLayout);
        assertEquals(rvh.getRecommendLayout(), null);
    }
}
