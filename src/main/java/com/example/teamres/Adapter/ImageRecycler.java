package com.example.teamres.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamres.Model.Image;
import com.example.teamres.Model.ImgDAO;
import com.example.teamres.R;
import com.example.teamres.Utils.DataConverter;
import com.example.teamres.Utils.ImageDatabase;
import com.example.teamres.Utils.Touch;

import java.util.List;

public class ImageRecycler extends RecyclerView.Adapter<ImageViewHolder> {

    List<Image> data;
    Context context;

    public ImageRecycler(List<Image> images, Context context) {
        this.context = context;
        this.data = images;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.promo_item, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {
        final Image img = data.get(position);
        Bitmap bmp = DataConverter.convertByteArraytoImage(img.getImage());

        holder.message.setText(img.getMessage());

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText pass = new EditText(context);
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Authorized Access");
                alert.setMessage("Please enter password to delete post");
                alert.setView(pass);



                alert.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String input = pass.getText().toString();

                        if(input.equals("0816")){
                            ImgDAO imgDAO = ImageDatabase.getDBInstance(context).imgDAO();
                            imgDAO.deleteImage(img);
                            data.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Message Deleted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context, "Invalid Password", Toast.LENGTH_SHORT).show();
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

    @Override
    public int getItemCount() {
        return data.size();
    }
}
