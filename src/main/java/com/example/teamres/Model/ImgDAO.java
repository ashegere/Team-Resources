package com.example.teamres.Model;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImgDAO {

    @Query("Select * from Images")
    List<Image> getAllImages();

    @Insert
    void insertImage(Image img);
    @Update
    void updateImage(Image img);
    @Delete
    void deleteImage(Image img);
}
