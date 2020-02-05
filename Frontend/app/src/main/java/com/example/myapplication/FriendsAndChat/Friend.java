package com.example.myapplication.FriendsAndChat;

import java.io.Serializable;
import java.util.ArrayList;

public class Friend implements Serializable {

    private String friendID;
    private String friendName;

    public Friend (String friendID, String friendName){
        this.friendID = friendID;
        this.friendName = friendName;
    }

    public String getFriendID() {
        return friendID;
    }

    public String getFriendName() { return friendName; }

}
