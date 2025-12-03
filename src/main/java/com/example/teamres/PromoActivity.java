package com.example.teamres;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.teamres.Model.Image;
import com.example.teamres.Model.ImgDAO;
import com.example.teamres.Utils.DataConverter;
import com.example.teamres.Utils.ImageDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.teamres.R.id.btnChoose;

public class PromoActivity extends AppCompatActivity {

    ImageButton choose;
    Button fab;
    static final int CAMERA_INTENT = 51;
    Bitmap bmpImage;
    ImgDAO imgDAO;
    EditText msg;
    ImageView imgView;
    Button listOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);
        init();

        bmpImage = null;
        imgDAO = ImageDatabase.getDBInstance(this).imgDAO();


        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CAMERA_INTENT);
                }

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image img = new Image();
                if(bmpImage == null || msg.getText().toString().isEmpty()) {
                    Toast.makeText(PromoActivity.this, "Please insert data", Toast.LENGTH_SHORT).show();
                }
                else{

                    img.setMessage(msg.getText().toString());
                    img.setImage(DataConverter.convertImagetoByteArray(bmpImage));

                    imgDAO.insertImage(img);
                    msg.setText("");
                    Toast.makeText(PromoActivity.this, "Promo has been inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromoActivity.this, PromoListActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode,  data);
        switch(requestCode) {
            case CAMERA_INTENT:
                if(resultCode == Activity.RESULT_OK) {
                    bmpImage = (Bitmap) data.getExtras().get("data");
                    if(bmpImage != null) {
                        imgView.setImageBitmap(bmpImage);
                    }
                }
                break;
        }
    }
    public void init() {
        choose = findViewById(R.id.btnChoose);
        msg = findViewById(R.id.edtName);
        fab = findViewById(R.id.btnAdd);
        imgView = findViewById(R.id.img);
        listOpen = findViewById(R.id.listOpen);
    }

}
