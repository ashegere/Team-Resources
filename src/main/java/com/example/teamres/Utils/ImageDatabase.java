package com.example.teamres.Utils;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.teamres.Model.Image;
import com.example.teamres.Model.ImgDAO;

@Database(
        entities = {Image.class},
        version = 2,
        exportSchema = false
)

public abstract class ImageDatabase extends RoomDatabase {
    private static ImageDatabase imgDB = null;

    public abstract ImgDAO imgDAO();

    public static synchronized ImageDatabase getDBInstance(Context context) {
        if(imgDB == null) {
            imgDB = Room.databaseBuilder(context.getApplicationContext(),
                    ImageDatabase.class, " images1").allowMainThreadQueries().build();
        }

        return imgDB;
    }
}
