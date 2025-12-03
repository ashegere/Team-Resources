package com.example.teamres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamres.Adapter.ToDoAdapter;
import com.example.teamres.Model.ToDoModel;
import com.example.teamres.Utils.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CommActivity extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;

    DBHelper dbh;

    private List<ToDoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm);

        CardView card = findViewById(R.id.card);
        card.setCardBackgroundColor(Color.rgb(87, 55, 250));

        dbh = new DBHelper(this);

        taskList = new ArrayList<>();

        tasksRecyclerView =findViewById(R.id.tasksRv);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(dbh, this);

        dbh.openDatabase();
        taskList = dbh.getAllTasks();
        tasksRecyclerView.setAdapter(tasksAdapter);
        tasksAdapter.setTasks(taskList);


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CommActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.new_task, null);
                final EditText newTask = mView.findViewById(R.id.newTasktxt);

                Button saveBtn = mView.findViewById(R.id.newTaskbtn);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String msg = newTask.getText().toString();

                        ToDoModel task = new ToDoModel();
                        task.setId(1);
                        task.setStatus(0);
                        task.setTask(msg);
                        task.setDate(java.text.DateFormat.getDateTimeInstance().format(new Date()));

                        dbh.insertTask(task);
                        taskList = dbh.getAllTasks();
                        tasksAdapter.setTasks(taskList);

                        Toast.makeText(CommActivity.this, "Message Saved", Toast.LENGTH_SHORT).show();
                        newTask.setText("");
                        dialog.cancel();


                    }
                });
            }
        });

    }



}