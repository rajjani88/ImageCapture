package com.raj.imagecapture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class Page2Activity extends AppCompatActivity {
    ImageView imageview;

    String uriStr = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        //find view by ids
        imageview = findViewById(R.id.imageview);

        Intent i = getIntent();
        uriStr = i.getStringExtra("img"); //receiving path as string

        Uri uri = Uri.parse(uriStr); //convert string to path
        imageview.setImageURI(uri);

    }
}