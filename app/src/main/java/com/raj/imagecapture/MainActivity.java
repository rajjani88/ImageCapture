package com.raj.imagecapture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button camera;
    Button selectGallery;
    Button gotoNext;
    ImageView imageview;
    String uriString = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find view by ids section
        gotoNext = findViewById(R.id.gotoNext);
        camera = findViewById(R.id.opencamera);
        selectGallery = findViewById(R.id.selectGallery);
        imageview = findViewById(R.id.imageview);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

        selectGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        gotoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new  Intent(MainActivity.this, Page2Activity.class);
                i.putExtra("img", uriString); //passing path to next page
                startActivity(i);
            }
        });
    }

    //open camera
    void openCamera(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 0);

    }

    //select from Gallery
    void selectImage(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }


    //get result
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){

                    Uri selectedImage = imageReturnedIntent.getData();
                    uriString = selectedImage.toString(); //storing uri (content path)
                    imageview.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    uriString = selectedImage.toString();//storing uri (content path)
                    imageview.setImageURI(selectedImage);
                }
                break;
        }
    }
}