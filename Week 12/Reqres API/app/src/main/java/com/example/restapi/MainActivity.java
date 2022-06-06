package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Reqres reqres = retrofit.create(Reqres.class);

        Call<List<User>> call = reqres.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if(!response.isSuccessful()){
                    textViewResult.setText("Code : " + response.code());
                    return;
                }

                List<User> users = response.body();

                for(User user : users){
                    String content = "";
                    content += "id" + user.getId() + "\n";
                    content += "email" + user.getEmail() + "\n";
                    content += "first_name" + user.getFirst_name() + "\n";
                    content += "last_name" + user.getLast_name() + "\n";
                    content += "avatar" + user.getAvatar() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
}