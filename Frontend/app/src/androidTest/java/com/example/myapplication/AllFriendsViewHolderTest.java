package com.example.myapplication;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.FriendsAndChat.AllFriends;
import com.example.myapplication.RecyclerViewHolder.AllFriendsViewHolder;
import com.example.myapplication.RecyclerViewHolder.PokeItemViewHolder;

import org.junit.Before;
import org.junit.Test;

import de.hdodenhof.circleimageview.CircleImageView;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AllFriendsViewHolderTest {

    private AllFriendsViewHolder pivh = mock(AllFriendsViewHolder.class);
    private View view = mock(View.class);
    private TextView textView = mock(TextView.class);
    private CircleImageView circleImageView = mock(CircleImageView.class);
    private RelativeLayout relativeLayout = mock(RelativeLayout.class);

    @Before
    public void intilizae() {

        pivh = new AllFriendsViewHolder(view);
    }

    @Test
    public void friendIDTest() {
        when(pivh.getUserID()).thenReturn(textView);
        assertEquals(pivh.getUserID(), null);
    }

    @Test
    public void friendNameTest() {
        when(pivh.getUserName()).thenReturn(textView);
        assertEquals(pivh.getUserName(), null);
    }

    @Test
    public void friendLayoutTest() {
        when(pivh.getAllFriendLayout()).thenReturn(relativeLayout);
        assertEquals(pivh.getAllFriendLayout(), null);
    }
}
