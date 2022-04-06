package com.example.uremind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.window.SplashScreen;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addBtn = findViewById(R.id.add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddTaskActivity.class));

            }
        });

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<DataTask> taskList = realm.where(DataTask.class).findAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TaskAdapter taskadapter = new TaskAdapter(getApplicationContext(),taskList);
        recyclerView.setAdapter(taskadapter);

        taskList.addChangeListener(new RealmChangeListener<RealmResults<DataTask>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChange(@NonNull RealmResults<DataTask> dataTasks) {
                taskadapter.notifyDataSetChanged();
            }
        });

    }
}