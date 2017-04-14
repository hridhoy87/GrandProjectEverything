package com.example.hridoy.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Hridoy on 10-07-16.
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView qus, test;
    Button returnData;
    RadioGroup selectionList;
    String gotBread , sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();

        SharedPreferences getSomething= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getSomething.getString("name","Bla Bla Bla....");
        String values = getSomething.getString("list","4");
        if(values.contentEquals("1")){
            qus.setText(et);
        }

        //getData();
        //qus.setText(gotBread);
    }

    private void getData() {
        Bundle gotBasket=getIntent().getExtras();
        gotBread= gotBasket.getString("key");
    }

    private void initialize() {
        qus=(TextView)findViewById(R.id.tvQ);
        test=(TextView)findViewById(R.id.tvText);
        returnData = (Button)findViewById(R.id.bReturn);
        selectionList = (RadioGroup)findViewById(R.id.rgAns);
        returnData.setOnClickListener(this);
        selectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent person=new Intent();
        Bundle bagpack = new Bundle();
        bagpack.putString("ans",sendData);
        person.putExtras(bagpack);
        setResult(RESULT_OK,person);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rCrazy:
                sendData = "Probably Right!!";
                break;
            case R.id.rSuperIdiot:
                sendData = "Noo..!!";
                break;
            case R.id.rBoth:
                sendData = "Not at all :p";
                break;
        }
        test.setText(sendData);
    }
}
