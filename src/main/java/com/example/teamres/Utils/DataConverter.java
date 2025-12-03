package com.example.teamres.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.io.ByteArrayOutputStream;

public class DataConverter {


    public static byte[] convertImagetoByteArray(Bitmap bp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 0, stream);

        return stream.toByteArray();
    }


    public static Bitmap convertByteArraytoImage(byte[] arr) {
        return BitmapFactory.decodeByteArray(arr, 0, arr.length);

    }
}

