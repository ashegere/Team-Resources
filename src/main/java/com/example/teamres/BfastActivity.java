package com.example.teamres;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamres.Adapter.AssignAdapter;
import com.example.teamres.Adapter.FoodAdapter;
import com.example.teamres.Model.AssignModel;
import com.example.teamres.Model.FoodModel;
import com.example.teamres.Utils.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BfastActivity extends AppCompatActivity {

    private RecyclerView fRview;
    private FoodAdapter adapter;

    DBHelper dbh;

    private List<FoodModel> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfast);

        dbh = new DBHelper(this);

        foodList = new ArrayList<>();

        fRview = findViewById(R.id.frcv);
        fRview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodAdapter(dbh, this);

        dbh.openDatabase();

        fRview.setAdapter(adapter);
        foodList = dbh.getAllFoods(1);
        fRview.setAdapter(adapter);
        adapter.setFoods(foodList);

        FloatingActionButton fab = findViewById(R.id.ffab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(BfastActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.new_task, null);
                final EditText foodItem = mView.findViewById(R.id.newTasktxt);

                Button saveBtn = mView.findViewById(R.id.newTaskbtn);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String msg = foodItem.getText().toString();

                        FoodModel food = new FoodModel();
                        food.setId(1);
                        food.setFoodName(msg);
                        dbh.insertFood(food,1);
                        foodList = dbh.getAllFoods(1);
                        adapter.setFoods(foodList);

                        Toast.makeText(BfastActivity.this, "Food Saved", Toast.LENGTH_SHORT).show();
                        foodItem.setText("");
                        dialog.cancel();

                    }
                });

            }
        });

        adapter.notifyDataSetChanged();

    }
}