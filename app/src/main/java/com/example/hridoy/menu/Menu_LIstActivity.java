package com.example.hridoy.menu;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu_LIstActivity extends ListActivity {

    String classes[]= { "StartingPoint", "TextPlay", "Camera", "Data",
            "Email","GFX","GFXSurface","SoundStaff",
            "Slider","Tabs","SimpleBrowser","Flipper","SharedPreferences","InternalData",
            "CameraStackOverflow","ExternalStorage","Accelerate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //MakingFull Screen;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView(R.layout.activity_main);
        setListAdapter(new ArrayAdapter<String>(Menu_LIstActivity.this,android.R.layout.simple_list_item_1,classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String theSelectedItem = classes[position];
        try {
        Class ourClass= Class.forName("com.example.hridoy.menu."+theSelectedItem);
        Intent intent= new Intent(Menu_LIstActivity.this,ourClass);
        startActivity(intent);}catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

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
}
