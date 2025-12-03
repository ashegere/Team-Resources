package com.example.teamres.Adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamres.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    TextView message;
    ImageButton deletebtn;



    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        message = itemView.findViewById(R.id.postTxt);
        deletebtn = itemView.findViewById(R.id.deletepostbtn);
    }
}
