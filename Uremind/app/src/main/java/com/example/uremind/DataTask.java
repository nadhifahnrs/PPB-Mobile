package com.example.uremind;

import io.realm.RealmObject;

public class DataTask extends RealmObject{
    String ntitle;
    String nnotes;
    long ncreatedTime;


    public String getTitle() {
        return ntitle;
    }

    public void setTitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNotes() {
        return nnotes;
    }

    public void setNotes(String nnotes) {
        this.nnotes = nnotes;
    }

    public long getCreatedTime() {
        return ncreatedTime;
    }

    public void setCreatedTime(long ncreatedTime) {
        this.ncreatedTime = ncreatedTime;
    }
}
