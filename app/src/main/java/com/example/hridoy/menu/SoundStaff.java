package com.example.hridoy.menu;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Hridoy on 12-07-16.
 */
public class SoundStaff extends Activity implements View.OnClickListener, View.OnLongClickListener {

    SoundPool sp;
    int explotion=0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v= new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sp=new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        explotion = sp.load(this,R.raw.one_last_breath,1);
        mp= MediaPlayer.create(this,R.raw.one_last_breath);

    }

    @Override
    public void onClick(View view) {
        if(explotion!=0)
        sp.play(explotion,1,1,0,0,1);
    }

    @Override
    public boolean onLongClick(View view) {
        mp.start();
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.release();
    }
}
