package com.example.hridoy.menu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by Hridoy on 11-07-16.
 */
public class GFX extends Activity {

    MyBringBack ourView;
    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Way Clock
        PowerManager pm= (PowerManager)getSystemService(Context.POWER_SERVICE);
        wakeLock=pm.newWakeLock(PowerManager.FULL_WAKE_LOCK,"whatever");

        super.onCreate(savedInstanceState);
        wakeLock.acquire();
        ourView = new MyBringBack(this);
        setContentView(ourView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
    }
}

