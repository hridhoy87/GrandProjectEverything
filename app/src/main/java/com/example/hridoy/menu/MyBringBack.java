package com.example.hridoy.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by Hridoy on 11-07-16.
 */
public class MyBringBack extends View {

    Bitmap bBall;
    float changingY;
    Typeface font;

    public MyBringBack(Context context) {
        super(context);
        bBall= BitmapFactory.decodeResource(getResources(),R.drawable.green_ball);
        changingY=0;
        font =Typeface.createFromAsset(context.getAssets(),"brushchi.TTF");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint textPaint=new Paint();
        textPaint.setARGB(50,1,1,1);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(75);
        textPaint.setTypeface(font);
        canvas.drawText("MyBringBack",canvas.getWidth()/2,200,textPaint);

        canvas.drawBitmap(bBall,canvas.getWidth()/2,changingY,null);
        if (changingY < canvas.getHeight()){
            changingY+=10;
        }else changingY=0;

        Rect middleRect= new Rect();
        middleRect.set(0,400,canvas.getWidth(),550);
        Paint ourBule = new Paint ();
        ourBule.setColor(Color.BLUE);
        canvas.drawRect(middleRect,ourBule);
        invalidate();
    }
}
