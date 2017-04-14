package com.example.hridoy.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by Hridoy on 12-07-16.
 */
public class Flipper extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    ViewFlipper flippy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        initialize();
        flippy.setFlipInterval(500);
        flippy.startFlipping();

    }

    private void initialize() {
        flippy=(ViewFlipper)findViewById(R.id.viewFlipper);
        //flippy.setOnLongClickListener(this);
        flippy.setOnClickListener(this);
    }

    @Override
    public boolean onLongClick(View view) {

        return false;
    }

    @Override
    public void onClick(View view) {
        flippy.showNext();
    }
}
