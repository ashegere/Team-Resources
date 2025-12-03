package com.example.teamres.Adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamres.CommActivity;
import com.example.teamres.Model.ToDoModel;
import com.example.teamres.R;
import com.example.teamres.Utils.DBHelper;

import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList;
    private CommActivity activity;
    DBHelper dbh;

    public ToDoAdapter(DBHelper dbh, CommActivity activity) {
        this.activity = activity;
        this.dbh = dbh;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(final ViewHolder holder, final int position){
        dbh.openDatabase();

        final ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.date.setText(item.getDate());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText pass = new EditText(activity);
                AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                alert.setTitle("Authorized Access");
                alert.setMessage("Please enter password to delete post");
                alert.setView(pass);



                alert.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = pass.getText().toString();

                        if(input.equals("0816")){
                            dbh.deleteTask(item.getId());
                            todoList.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(activity, "Message Deleted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(activity, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });

                AlertDialog al = alert.create();
                al.show();

            }
        });

}
    public int getItemCount() {
        return todoList.size();
}

    private boolean toBoolean(int n) {
        return n!=0;
    }

    public void setTasks(List<ToDoModel> todoList) {
        Collections.reverse(todoList);
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        TextView date;
        ImageButton btn;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheck);
            date = view.findViewById(R.id.datetxt);
            btn = view.findViewById(R.id.deletebtn);
        }
    }
}
