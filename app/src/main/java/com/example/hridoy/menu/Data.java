package com.example.hridoy.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Hridoy on 10-07-16.
 */
public class Data extends Activity implements View.OnClickListener {

    Button start,startFor;
    EditText sendET;
    TextView gotAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initialize();
    }

    private void initialize() {
        start=(Button)findViewById(R.id.bSA);
        startFor=(Button)findViewById(R.id.bSAFR);
        sendET=(EditText)findViewById(R.id.etSend);
        gotAns=(TextView)findViewById(R.id.tvGot);

        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bSA:
                String bread = sendET.getText().toString();
                Bundle basket= new Bundle();
                basket.putString("key",bread);
                Intent intent = new Intent(Data.this,OpenedClass.class);
                intent.putExtras(basket);
                startActivity(intent);
                break;
            case R.id.bSAFR:
                Intent intent1= new Intent(Data.this,OpenedClass.class);
                startActivityForResult(intent1,0);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            String s=bundle.getString("ans");
            gotAns.setText(s);
        }
    }
}
