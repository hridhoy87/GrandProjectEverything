package com.example.hridoy.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hridoy on 10-07-16.
 */
public class Camera extends Activity implements View.OnClickListener {

    ImageButton ib;
    Button b;
    ImageView iv;
    Intent intent;
    private final static int CAMERA_REQUEST=1888;
    Bitmap bmp;
    InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initialize();
        inputStream = getResources().openRawResource(R.raw.asad_1080p);
        bmp = BitmapFactory.decodeStream(inputStream);
    }

    private void initialize() {
        ib=(ImageButton)findViewById(R.id.imageB);
        b=(Button)findViewById(R.id.bSetWall);
        iv=(ImageView)findViewById(R.id.iv);
        b.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bSetWall:
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.imageB:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try{
                    startActivityForResult(intent,RESULT_OK);
                }catch (RuntimeException e){
                    e.printStackTrace();
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST && resultCode== Activity.RESULT_OK){
            bmp= (Bitmap)data.getExtras().get("data");
            iv.setImageBitmap(bmp);
            /*Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);*/

        }
    }


}
