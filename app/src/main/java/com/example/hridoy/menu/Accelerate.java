package com.example.hridoy.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Hridoy on 16-07-16.
 */


public class Accelerate extends Activity implements SensorEventListener {

    float x;
    float y;
    static float sensorx;
    static float sensory;
    static Bitmap ball;
    SensorManager sm;
    MyBringBackSurfaceView ourSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
            Sensor s=sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
        }

        ball= BitmapFactory.decodeResource(getResources(),R.drawable.green_ball);

        x=y=sensorx=sensory=0;

        ourSurfaceView = new MyBringBackSurfaceView(this);
        ourSurfaceView.resume();
        setContentView(ourSurfaceView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorx=sensorEvent.values[0];
        sensory=sensorEvent.values[1];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


    }

    static float getSensorX(){
        return sensorx;
    }
    static float getSensorY(){
        return sensory;
    }
    static Bitmap getBitmap(){
        return ball;
    }
}





class MyBringBackSurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder ourHolder;
    Thread ourThread;
    boolean isRunning=false;

    public MyBringBackSurfaceView(Context context) {
        super(context);
        ourHolder=getHolder();
    }

    public void pause(){

        isRunning=false;
        while(true){
            try {
                ourThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        ourThread=null;
    }
    public void resume(){
        isRunning=true;
        ourThread=new Thread(this);
        ourThread.start();
    }

    @Override
    public void run() {
        while(isRunning){
            if( !ourHolder.getSurface().isValid())
                continue;
            Canvas canvas=ourHolder.lockCanvas();
            canvas.drawRGB(50,165,155);

            float centerX = canvas.getWidth()/2;
            float centerY = canvas.getHeight()/2;

            Bitmap ball= Accelerate.getBitmap();
            float sensorx= Accelerate.getSensorX();
            float sensory=Accelerate.getSensorY();

            canvas.drawBitmap
                    (ball,
                    centerX+sensorx*20,
                    centerY+sensory*20,
                    null);

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }
}
