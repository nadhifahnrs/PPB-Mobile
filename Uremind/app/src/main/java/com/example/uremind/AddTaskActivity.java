package com.example.uremind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText tasktitle = findViewById(R.id.Tasktitle);
        EditText tasknotes = findViewById(R.id.Tasknotes);
        MaterialButton saveBtn = findViewById(R.id.savebtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String title = tasktitle.getText().toString();
                String notes = tasknotes.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                DataTask task = realm.createObject(DataTask.class);
                task.setTitle(title);
                task.setNotes(notes);
                task.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Task Saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}