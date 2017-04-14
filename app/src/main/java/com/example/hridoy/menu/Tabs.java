package com.example.hridoy.menu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by Hridoy on 12-07-16.
 */
public class Tabs extends AppCompatActivity {
    TabHost th;
    Button newTab;
    long Start,Stop;
    TextView showResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        initii();
        creatingFirstTab();
        creatingSecondTab();
        creatingThirdTab();
        //th.setup();
        //TabHost.TabSpec ts= th.newTabSpec("tag1");
    }

    private void initii() {
        Start=0;
        th=(TabHost)findViewById(R.id.tabHost);
        newTab=(Button)findViewById(R.id.bAddTab);
        showResult=(TextView)findViewById(R.id.tvShowResults);
        newTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addATab();

            }
        });
        Button bStart=(Button)findViewById(R.id.bStartWatch);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start=System.currentTimeMillis();
                showResult.setText("Counting...");

            }
        });
        Button bPause=(Button)findViewById(R.id.bPause);
        bPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stop=System.currentTimeMillis();
                if(Start!=0){
                    long result=Stop-Start;
                    int millis = (int)result;
                    int seconds= (int)result/1000;
                    int mins= seconds/60;
                    int hrs=mins/60;
                    millis=millis%100;
                    seconds=seconds%60;
                    mins=mins%60;
                    showResult.setText(String.format("%d,%d,%d,%d",hrs,mins,seconds,millis));
                }
            }
        });
        Button bStop=(Button)findViewById(R.id.bStopWatch);
        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start=Stop=System.currentTimeMillis();
                showResult.setText(String.format("%d:%d:%d:%d",0,0,0,0));

                /*if(Start!=0){
                    long result=Stop-Start;
                    int millis = (int)result;
                    int seconds= (int)result/1000;
                    int mins= seconds/60;
                    int hrs=mins/60;
                    millis=millis%100;
                    seconds=seconds%60;
                    mins=mins%60;
                    showResult.setText(String.format("%d,%d,%d,%d",hrs,mins,seconds,millis));
                }*/
            }
        });
    }

    private void addATab() {
        th.setup();
        TabHost.TabSpec ts= th.newTabSpec("tagJustAdd");
        ts.setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String s) {/*
                TextView textView=new TextView(Tabs.this);
                textView.setText("You Have Created a New Tab");*/
                //return (textView);
                View view = new View(Tabs.this,null,R.layout.tabs);
                return (view);
            }
        });
        ts.setIndicator("New Tab");
        th.addTab(ts);
    }

    private void creatingFirstTab() {
        th.setup();
        TabHost.TabSpec ts= th.newTabSpec("tag1");
        ts.setContent(R.id.tab1);
        ts.setIndicator("Stop Watch");
        th.addTab(ts);
    }

    private void creatingSecondTab() {
        th.setup();
        TabHost.TabSpec ts= th.newTabSpec("tag2");
        ts.setContent(R.id.tab2);
        ts.setIndicator("Tab 2");
        th.addTab(ts);

    }

    private void creatingThirdTab() {
        th.setup();
        TabHost.TabSpec ts= th.newTabSpec("tag3");
        ts.setContent(R.id.tab3);
        ts.setIndicator("+Tab");
        th.addTab(ts);
    }

}
