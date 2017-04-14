package com.example.hridoy.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CameraStackOverflow extends AppCompatActivity {

    //I don't know why 1888.....................
    private static final int CAMERA_REQUEST=1888;

    private ImageView imageView;
    @Override
    //Why "public void" if it was "protected void" as it was then what would happen?
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_stack_overflow);
        initialize();
    }

    private void initialize() {
        this.imageView=(ImageView)findViewById(R.id.imageView1);
        Button photoButton=(Button)findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST,null);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST && resultCode== Activity.RESULT_OK){
            Bitmap photo= (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
