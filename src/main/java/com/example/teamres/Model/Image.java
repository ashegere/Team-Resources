package com.example.teamres.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Images")
public class Image {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "message")
    String message;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] image;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
