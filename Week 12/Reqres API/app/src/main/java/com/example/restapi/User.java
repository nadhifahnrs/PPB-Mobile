package com.example.restapi;

import com.google.gson.annotations.SerializedName;

public class User {
    private int id;
    private String email;
    private String first_name;
    private String last_name;

    @SerializedName("avatar")
    private String avatar;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAvatar() {
        return avatar;
    }
}
