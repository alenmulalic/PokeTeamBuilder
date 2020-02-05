package com.example.myapplication.user;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {
    private String userId;
    private String userTeamId;

    public Users(String userId, String userTeamId){
        this.userId = userId;
        this.userTeamId = userTeamId;
    }

    public String getUserId() {
        return userId;
    }
    public String getUserTeamId() {
        return userTeamId;
    }
}
