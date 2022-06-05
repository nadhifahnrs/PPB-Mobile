package com.example.uremind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        // Inisialisasi
        EditText task_title;
        EditText task_note;
        Button btnSave;

        RecyclerView recyclerView;
        List<MainData> dataList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager;
        RoomDB database;
        MainAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Inisiasi
            task_title = findViewById(R.id.Tasktitle);
            task_note = findViewById(R.id.Tasknotes);
            btnSave = findViewById(R.id.btn_save);
            recyclerView = findViewById(R.id.recyclerview);

            database = RoomDB.getInstance(this);
            //Store database value in data list
            dataList = database.mainDao().getAll();

            //Inisialisasi linear layout manager
            linearLayoutManager = new LinearLayoutManager(this);
            //Set Layout manager
            recyclerView.setLayoutManager(linearLayoutManager);
            //Inisialisasi adapter
            adapter = new MainAdapter(MainActivity.this,dataList);
            //Set Adapter
            recyclerView.setAdapter(adapter);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Get string from edit text
                    String sText = task_title.getText().toString().trim();
                    String sNote = task_note.getText().toString().trim();

                    //inisialisasi main data
                    MainData data = new MainData();
                    //Set text on main data
                    data.setTitletask(sText);
                    data.setNotetask(sNote);
                    //Insert text in database
                    database.mainDao().insert(data);

                    //clear task
                    task_title.setText("");
                    task_note.setText("");
                    //notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
