package com.example.teamres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button empbtn = findViewById(R.id.empcom);
        Button promo = findViewById(R.id.promo);
        Button ctc = findViewById(R.id.ctc);
        Button jobbtn = findViewById(R.id.jobbtn);
        Button ffoodbtn = findViewById(R.id.ffoodbtn);


        CardView cview = findViewById(R.id.cview);


        cview.setCardBackgroundColor(Color.WHITE);

        GradientDrawable bgShape = (GradientDrawable)empbtn.getBackground();
        bgShape.setColor(Color.rgb(121, 0, 181));

        GradientDrawable bgShape2 = (GradientDrawable)promo.getBackground();
        bgShape2.setColor(Color.rgb(246, 89, 6));

        GradientDrawable bgShape3 = (GradientDrawable)ctc.getBackground();
        bgShape3.setColor(Color.rgb(232, 143, 0));

        GradientDrawable bgShape4 = (GradientDrawable)jobbtn.getBackground();
        bgShape4.setColor(Color.rgb(36, 91, 161));

        GradientDrawable bgShape5 = (GradientDrawable)ffoodbtn.getBackground();
        bgShape5.setColor(Color.rgb(5, 115, 45));


        empbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(MainActivity.this, CommActivity.class);
                startActivity(in);
            }
        });

        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(MainActivity.this, PromoActivity.class);
                startActivity(in);
            }
        });
        ctc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(MainActivity.this, ContactActivity.class);
                startActivity(in);
            }
        });


        jobbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(MainActivity.this, JobAssignActivity.class);
                startActivity(in);
            }
        });

        ffoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(MainActivity.this, FfoodActivity.class);
                startActivity(in);
            }
        });
}
}