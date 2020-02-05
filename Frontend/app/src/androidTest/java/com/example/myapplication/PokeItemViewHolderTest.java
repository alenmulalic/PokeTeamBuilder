package com.example.myapplication;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.RecyclerViewHolder.PokeItemViewHolder;

import org.junit.Before;
import org.junit.Test;

import de.hdodenhof.circleimageview.CircleImageView;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PokeItemViewHolderTest {

    private PokeItemViewHolder pivh = mock(PokeItemViewHolder.class);
    private View view = mock(View.class);
    private TextView textView = mock(TextView.class);
    private CircleImageView circleImageView = mock(CircleImageView.class);
    private RelativeLayout relativeLayout = mock(RelativeLayout.class);

    @Before
    public void intilizae() {

        pivh = new PokeItemViewHolder(view);
    }

    @Test
    public void pokeNOTest() {
        when(pivh.getItemName()).thenReturn(textView);
        assertEquals(pivh.getItemName(), null);
    }

    @Test
    public void pokeImageTest() {
        when(pivh.getImage()).thenReturn(circleImageView);
        assertEquals(pivh.getImage(), null);
    }

    @Test
    public void pokeLayoutTest() {
        when(pivh.getItemlist()).thenReturn(relativeLayout);
        assertEquals(pivh.getItemlist(), null);
    }
}
