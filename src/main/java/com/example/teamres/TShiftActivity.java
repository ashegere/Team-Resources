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
import com.example.teamres.Model.AssignModel;
import com.example.teamres.Utils.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TShiftActivity extends AppCompatActivity {

    private RecyclerView fRecyclerView;
    private AssignAdapter adapter;


    DBHelper dbh;

    private List<AssignModel> assnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_shift);

        dbh = new DBHelper(this);
        Button clearbtn = findViewById(R.id.clearbtn);

        assnList = new ArrayList<>();

        fRecyclerView = findViewById(R.id.sShiftRv);
        fRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssignAdapter(dbh, this);

        dbh.openDatabase();

        fRecyclerView.setAdapter(adapter);

        assnList = dbh.getAllAssignments(3);
        fRecyclerView.setAdapter(adapter);

        adapter.setAssn(assnList);


        FloatingActionButton fab = findViewById(R.id.ffab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(TShiftActivity.this);
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

                        AssignModel assn = new AssignModel();
                        assn.setId(1);
                        assn.setAssn(msg);
                        assn.setStatus(0);
                        assn.setDate(null);

                        dbh.insertAssignment(assn,3);
                        assnList = dbh.getAllAssignments(3);
                        adapter.setAssn(assnList);

                        Toast.makeText(TShiftActivity.this, "Message Saved", Toast.LENGTH_SHORT).show();
                        newTask.setText("");
                        dialog.cancel();

                    }
                });
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < assnList.size();i++) {
                    AssignModel as = assnList.get(i);
                    as.setDate(null);
                    as.setStatus(0);
                    dbh.updateStatus(as, as.getId(), 3);
                    dbh.updateDate(as, as.getId(), 3);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}