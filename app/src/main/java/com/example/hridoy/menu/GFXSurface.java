package com.example.hridoy.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Hridoy on 11-07-16.
 */
public class GFXSurface extends Activity implements View.OnTouchListener{

    MyBringBackSurfaceView ourSurfaceView;
    float x,y,sX,sY,Fx,Fy,dx,dy,aniX,aniY,scaleX,scaleY;
    Bitmap test, diamond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyBringBackSurfaceView(this);
        ourSurfaceView.setOnTouchListener(this);

        x=y=sX=sY=Fx=Fy=dx=dy=aniX=aniY=scaleY=scaleX=0;

        test= BitmapFactory.decodeResource(getResources(),R.drawable.green_ball);
        diamond= BitmapFactory.decodeResource(getResources(),R.drawable.diamonda);

        setContentView(ourSurfaceView);


    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    /*@Override
    protected void onPostResume() {
        super.onPostResume();

    }*/

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }*/

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        //FPS(Frames Per Second) ডিফাইন করে দেওয়া হচ্ছে; To Get More Processing Time :)
        try {
            Thread.sleep((long) 16.7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x=motionEvent.getX();
        y=motionEvent.getY();

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                sX=motionEvent.getX();
                sY=motionEvent.getY();
                Fx=Fy=dx=dy=aniX=aniY=scaleY=scaleX=0;
                break;
            case MotionEvent.ACTION_UP:
                Fx=motionEvent.getX();
                Fy=motionEvent.getY();
                dx=Fx-sX;
                dy=Fy-sY;
                scaleX=dx/30;
                scaleY=dy/30;
                x=y=0;
                break;

        }
        return true;
        //return false ---> bitmap will not follow;
    }

    public class MyBringBackSurfaceView extends SurfaceView implements Runnable {

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
                if ( x!=0 && y!=0 ) {
                    canvas.drawBitmap(test,x-test.getWidth()/2,y-test.getHeight()/2,null);
                }
                if ( sX!=0 && sY!=0 ) {
                    canvas.drawBitmap(diamond,sX-diamond.getWidth()/2,sY-diamond.getHeight()/2,null);
                }
                if ( Fx!=0 && Fy!=0 ) {
                    canvas.drawBitmap(test,Fx-test.getWidth()/2-aniX,Fy-test.getHeight()/2-aniY,null);
                    canvas.drawBitmap(diamond,Fx-diamond.getWidth()/2,Fy-diamond.getHeight()/2,null);
                }
                aniX=aniX+scaleX;
                aniY=aniY+scaleY;

                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

}
