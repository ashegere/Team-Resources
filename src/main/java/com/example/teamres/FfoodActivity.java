package com.example.teamres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FfoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffood);

        Button bfastBtn = findViewById(R.id.bfastbtn);
        Button lunchBtn = findViewById(R.id.lunchbtn);
        Button dinnerBtn = findViewById(R.id.dinnerbtn);

        GradientDrawable btnBg = (GradientDrawable)bfastBtn.getBackground();
        btnBg.setColor(Color.rgb(74, 199, 58));

        GradientDrawable btnBg2 = (GradientDrawable)lunchBtn.getBackground();
        btnBg2.setColor(Color.rgb(52, 156, 191));

        GradientDrawable btnBg3 = (GradientDrawable)dinnerBtn.getBackground();
        btnBg3.setColor(Color.rgb(191, 52, 52));

        bfastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(FfoodActivity.this, BfastActivity.class);
                startActivity(in);
            }
        });

        lunchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(FfoodActivity.this, LunchActivity.class);
                startActivity(in);
            }
        });

        dinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(FfoodActivity.this, DinnerActivity.class);
                startActivity(in);
            }
        });
    }
}