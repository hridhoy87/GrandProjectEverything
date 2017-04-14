package com.example.hridoy.menu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by Hridoy on 10-07-16.
 */
public class TextPlay extends Activity {

    Button chkCmd;
    ToggleButton passTog;
    EditText input;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.text_play);

        baconAndEggs();

        passTog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passTog.isChecked()){
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

        chkCmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = input.getText().toString();
                display.setText("Check");
                if(check.contentEquals("left") ){
                    display.setGravity(Gravity.LEFT);
                }else if (check.contentEquals("right") ){
                    display.setGravity(Gravity.RIGHT);
                }else  if (check.contentEquals("center") ){
                    display.setGravity(Gravity.CENTER);
                }else  if (check.contentEquals("blue") ){
                    display.setText("BLUE");
                    display.setTextColor(Color.BLUE);
                }else  if (check.contentEquals("WTF") ){
                    Random crazy= new Random();
                    display.setText("WTF!!!");
                    display.setTextSize(crazy.nextInt(100));
                    display.setTextColor(Color.rgb(crazy.nextInt(265),crazy.nextInt(265),crazy.nextInt(265)));
                    //display.setGravity(Gravity.getAbsoluteGravity(crazy.nextInt(100),crazy.nextInt(100)));
                    switch (crazy.nextInt(3)){
                        case 0:
                            display.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            display.setGravity(Gravity.CENTER);
                            break;
                        case 2:
                            display.setGravity(Gravity.RIGHT);
                            break;
                    }
                }else{
                    display.setText("invalid");
                    display.setGravity(Gravity.CENTER);
                    display.setTextColor(Color.BLACK);
                }
            }
        });
    }

    private void baconAndEggs() {
        chkCmd =(Button) findViewById(R.id.bResults);
        passTog = (ToggleButton)findViewById(R.id.tbPassword);
        input = (EditText) findViewById(R.id.etCommand);
        display = (TextView) findViewById(R.id.tvResults);
    }
}
