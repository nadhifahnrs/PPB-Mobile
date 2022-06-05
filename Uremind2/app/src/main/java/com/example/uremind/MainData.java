package com.example.uremind;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// table name
@Entity(tableName = "table_name")
public class MainData implements Serializable {
    // Kolom ID
    @PrimaryKey(autoGenerate = true)
    private int ID;

    // Kolom title task
    @ColumnInfo(name = "task")
    private String titletask;

    // Kolom note task
    @ColumnInfo(name = "note")
    private String notetask;

    // Setter & Getter

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitletask() {
        return titletask;
    }

    public void setTitletask(String titletask) {
        this.titletask = titletask;
    }

    public String getNotetask() {
        return notetask;
    }

    public void setNotetask(String notetask) {
        this.notetask = notetask;
    }
}
