package com.example.teamres.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamres.CommActivity;
import com.example.teamres.FshiftActivity;
import com.example.teamres.JobAssignActivity;
import com.example.teamres.Model.AssignModel;
import com.example.teamres.R;
import com.example.teamres.SshiftActivity;
import com.example.teamres.TShiftActivity;
import com.example.teamres.Utils.DBHelper;

import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class AssignAdapter extends RecyclerView.Adapter<AssignAdapter.ViewHolder>{
    private List<AssignModel> assnList;
    DBHelper dbh;
    JobAssignActivity activity;
    Context ctc;

    public AssignAdapter(DBHelper dbh, Context ctc) {
        this.ctc = ctc;
        this.dbh = dbh;

    }

    public AssignAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);

        return new AssignAdapter.ViewHolder(itemView);
    }

    public void onBindViewHolder(final AssignAdapter.ViewHolder holder, final int position){
        dbh.openDatabase();

        final AssignModel item = assnList.get(position);
        holder.assignment.setText(item.getAssn());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText pass = new EditText(ctc);
                AlertDialog.Builder alert = new AlertDialog.Builder(ctc);
                alert.setTitle("Authorized Access");
                alert.setMessage("Please enter password to delete post");
                alert.setView(pass);

                alert.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = pass.getText().toString();

                        if(input.equals("0816")){
                            if (ctc instanceof FshiftActivity) {
                                dbh.deleteAssignment(item.getId(), 1);
                                assnList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(ctc, "Message Deleted", Toast.LENGTH_SHORT).show();
                            }

                            if (ctc instanceof SshiftActivity) {
                                dbh.deleteAssignment(item.getId(), 2);
                                assnList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(ctc, "Message Deleted", Toast.LENGTH_SHORT).show();
                            }

                            if (ctc instanceof TShiftActivity) {
                                dbh.deleteAssignment(item.getId(), 3);
                                assnList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(ctc, "Message Deleted", Toast.LENGTH_SHORT).show();
                            }
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
                alert.show();



            }
        });
        holder.assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alert = new AlertDialog.Builder(ctc);
                alert.setTitle("Caution");
                alert.setMessage("Are you sure you completed this task?");
                alert.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                if (ctc instanceof FshiftActivity) {
                                    item.setDate(java.text.DateFormat.getDateTimeInstance().format(new Date()));
                                    item.setStatus(1);
                                    dbh.updateDate(item, item.getId(), 1);
                                    dbh.updateStatus(item, item.getId(), 1);
                                    notifyDataSetChanged();
                                }
                                if (ctc instanceof SshiftActivity) {
                                    item.setDate(java.text.DateFormat.getDateTimeInstance().format(new Date()));
                                    item.setStatus(1);
                                    dbh.updateDate(item, item.getId(), 2);
                                    dbh.updateStatus(item, item.getId(), 2);
                                    notifyDataSetChanged();
                                }
                                if (ctc instanceof TShiftActivity) {
                                    item.setDate(java.text.DateFormat.getDateTimeInstance().format(new Date()));
                                    item.setStatus(1);
                                    dbh.updateDate(item, item.getId(), 3);
                                    dbh.updateStatus(item, item.getId(), 3);
                                    notifyDataSetChanged();
                                }
                            }
                        });
                alert.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                holder.assignment.setChecked(false);
                            }
                        });
                AlertDialog al = alert.create();
                al.show();
            }
        });

        if (ctc instanceof FshiftActivity) {
            holder.dateMsg.setText(dbh.getDate(item.getId(),1 ));
        }
        if (ctc instanceof SshiftActivity) {
            holder.dateMsg.setText(dbh.getDate(item.getId(), 2));
        }
        if (ctc instanceof TShiftActivity) {
            holder.dateMsg.setText(dbh.getDate(item.getId(), 3));
        }




        if (ctc instanceof FshiftActivity) {
            holder.assignment.setChecked(dbh.statusTrue(item.getId(),1));
        }

        if (ctc instanceof SshiftActivity) {
            holder.assignment.setChecked(dbh.statusTrue(item.getId(),2));
        }

        if (ctc instanceof TShiftActivity) {
            holder.assignment.setChecked(dbh.statusTrue(item.getId(),3));
        }

    }


    public int getItemCount() {
        return assnList.size();
    }


    public void setAssn(List<AssignModel> assnList) {
        this.assnList = assnList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox assignment;
        ImageButton btn;
        TextView dateMsg;

        ViewHolder(View view) {
            super(view);
            assignment = view.findViewById(R.id.todoCheck);
            btn = view.findViewById(R.id.deletebtn);
            dateMsg = view.findViewById(R.id.datetxt);
        }
    }
}
