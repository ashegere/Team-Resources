package com.example.teamres;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamres.Adapter.AssignAdapter;
import com.example.teamres.Model.AssignModel;
import com.example.teamres.Utils.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class JobAssignActivity extends AppCompatActivity {

    Button first, second, third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_assign);

        first = findViewById(R.id.fbutton);
        second = findViewById(R.id.sbutton);
        third = findViewById(R.id.tbutton);

        CardView card = findViewById(R.id.card);
        card.setCardBackgroundColor(Color.rgb(0, 0, 128));
        first.setBackgroundColor(Color.rgb(232, 124, 0));
        second.setBackgroundColor(Color.rgb(2, 210, 217));
        third.setBackgroundColor(Color.rgb(128, 4, 222));


        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(JobAssignActivity.this, FshiftActivity.class);
                startActivity(in);
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(JobAssignActivity.this, SshiftActivity.class);
                startActivity(in);
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClass(JobAssignActivity.this, TShiftActivity.class);
                startActivity(in);
            }
        });

    }
}