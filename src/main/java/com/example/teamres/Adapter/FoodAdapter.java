package com.example.teamres.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamres.BfastActivity;
import com.example.teamres.DinnerActivity;
import com.example.teamres.FshiftActivity;
import com.example.teamres.LunchActivity;
import com.example.teamres.Model.AssignModel;
import com.example.teamres.Model.FoodModel;
import com.example.teamres.Model.Image;
import com.example.teamres.Model.ToDoModel;
import com.example.teamres.R;
import com.example.teamres.SshiftActivity;
import com.example.teamres.TShiftActivity;
import com.example.teamres.Utils.DBHelper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<FoodModel> foodList;
    DBHelper dbh;
    Context ctc;

    public FoodAdapter(DBHelper dbh, Context ctc) {
        this.ctc = ctc;
        this.dbh = dbh;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FoodAdapter.ViewHolder holder, int position) {
        dbh.openDatabase();

        final FoodModel fItem = foodList.get(position);
        holder.foodName.setText(fItem.getFoodName());

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText pass = new EditText(ctc);
                AlertDialog.Builder alert = new AlertDialog.Builder(ctc);
                alert.setTitle("Edit Food");
                alert.setView(pass);

                alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = pass.getText().toString();

                        if (ctc instanceof BfastActivity) {
                            fItem.setFoodName(input);
                            dbh.updateFood(fItem, 1,1);
                            notifyDataSetChanged();
                            Toast.makeText(ctc, "Food Updated", Toast.LENGTH_SHORT).show();
                        }

                        if (ctc instanceof LunchActivity) {
                            fItem.setFoodName(input);
                            dbh.updateFood(fItem, 1,2);
                            notifyDataSetChanged();
                            Toast.makeText(ctc, "Food Updated", Toast.LENGTH_SHORT).show();
                        }

                        if (ctc instanceof DinnerActivity) {
                            fItem.setFoodName(input);
                            dbh.updateFood(fItem, 1,3);
                            notifyDataSetChanged();
                            Toast.makeText(ctc, "Food Updated", Toast.LENGTH_SHORT).show();
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


        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
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
                            if (ctc instanceof BfastActivity) {
                                dbh.deleteFood(fItem.getId(), 1);
                                foodList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(ctc, "Food Deleted", Toast.LENGTH_SHORT).show();
                            }

                            if (ctc instanceof LunchActivity) {
                                dbh.deleteFood(fItem.getId(), 2);
                                foodList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(ctc, "Food Deleted", Toast.LENGTH_SHORT).show();
                            }

                            if (ctc instanceof DinnerActivity) {
                                dbh.deleteFood(fItem.getId(), 3);
                                foodList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(ctc, "Food Deleted", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(ctc, "Invalid Password", Toast.LENGTH_SHORT).show();
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

    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setFoods(List<FoodModel> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        ImageButton editBtn, deleteBtn;

        ViewHolder(View view) {
            super(view);
            foodName = view.findViewById(R.id.foodName);
            editBtn = view.findViewById(R.id.editBtn);
            deleteBtn = view.findViewById(R.id.deleteBtn);
        }
    }
}


