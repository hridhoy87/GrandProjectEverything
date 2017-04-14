package com.example.hridoy.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Hridoy on 12-07-16.
 */
public class SharedPreferences extends Activity {

    android.content.SharedPreferences.Editor editor;
    EditText sharedData;
    Button bSave,bLoad;
    TextView dataResult;
    String sData;
    android.content.SharedPreferences sharedString;
    public static String myFileName="My Shared File";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences);
        initialize();
    }

    private void initialize() {
        sharedData=(EditText)findViewById(R.id.et);
        bSave=(Button)findViewById(R.id.bSave);
        bLoad=(Button)findViewById(R.id.bLoad);
        dataResult=(TextView) findViewById(R.id.tvLoadedResult);

        sharedString=getSharedPreferences(myFileName,0);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sData=sharedData.getText().toString();
                editor=sharedString.edit();
                editor.putString("ourSharedString",sData);
                editor.commit();
            }
        });
        bLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataReturned=sharedString.getString("ourSharedString","COULDN'T LOAD........");
                dataResult.setText(dataReturned);
            }
        });
    }
}
