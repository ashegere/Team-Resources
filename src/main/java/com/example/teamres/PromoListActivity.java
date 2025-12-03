package com.example.teamres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.teamres.Adapter.ImageRecycler;
import com.example.teamres.Model.ImgDAO;
import com.example.teamres.Utils.ImageDatabase;

public class PromoListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImgDAO imgDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_list);

        recyclerView = findViewById(R.id.rcView);

        imgDAO = ImageDatabase.getDBInstance(this).imgDAO();

        ImageRecycler imageRecycler = new ImageRecycler(imgDAO.getAllImages(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(imageRecycler);
    }
}