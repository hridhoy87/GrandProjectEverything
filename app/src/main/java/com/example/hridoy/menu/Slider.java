package com.example.hridoy.menu;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

public class Slider extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SlidingDrawer.OnDrawerOpenListener {

    Button handle,firstButton,secondButton,thirdButton,fourthButton,fifthButton;
    CheckBox checkBox;
    SlidingDrawer sd;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider);
        initialize();
    }

    private void initialize() {
        handle=(Button)findViewById(R.id.button);
        firstButton=(Button)findViewById(R.id.bFirst);
        secondButton=(Button)findViewById(R.id.bSecond);
        thirdButton=(Button)findViewById(R.id.bThird);
        fourthButton=(Button)findViewById(R.id.bFourth);
        fifthButton=(Button)findViewById(R.id.bFifth);
        sd =(SlidingDrawer)findViewById(R.id.slidingDrawer);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        mp = MediaPlayer.create(this,R.raw.one_last_breath);

        sd.setOnDrawerOpenListener(this);

        firstButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        thirdButton.setOnClickListener(this);
        fourthButton.setOnClickListener(this);
        fifthButton.setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bFirst:
                sd.open();
                //sd.animate();
                break;
            case R.id.bSecond:
                //sd.animateOpen();
                break;
            case R.id.bThird:
                //sd.animateClose();
                break;
            case R.id.bFourth:
                sd.toggle();
                break;
            case R.id.bFifth:
                sd.close();
                mp.stop();
                break;
            case R.id.button:
                if(sd.isActivated())
                    mp.start();
                else mp.stop();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(compoundButton.isChecked()){
            sd.lock();
        }else{
            sd.unlock();
        }

    }

    @Override
    public void onDrawerOpened() {
        if(! sd.isOpened())
            mp.stop();
        else
            mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }
}
