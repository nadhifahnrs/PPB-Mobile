package com.example.restapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface Reqres {

    @GET("api/users?page=2")
    Call<List<User>> getUsers();

}
