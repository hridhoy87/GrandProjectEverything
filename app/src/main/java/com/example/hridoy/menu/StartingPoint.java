package com.example.hridoy.menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class StartingPoint extends AppCompatActivity {

    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Making It FullScreen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_starting_point);

        ourSong = MediaPlayer.create(StartingPoint.this,R.raw.one_last_breath);

        SharedPreferences getPrefs= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music =getPrefs.getBoolean("check_box",true);
        if(music = true) ourSong.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
    }


    //......Not Sure Part....................
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp=getMenuInflater();
        blowUp.inflate(R.menu.cool_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.aboutUs:
                Intent intent= new Intent("com.example.hridoy.ABOUT");
                startActivity(intent);
                break;
            case R.id.preferences:
                Intent P= new Intent("com.example.hridoy.PREFS");
                startActivity(P);
                break;
            case R.id.exit:
                finish();
                break;
        }

        return true;
    }
    //Up To Here....................*******....................*/
}
