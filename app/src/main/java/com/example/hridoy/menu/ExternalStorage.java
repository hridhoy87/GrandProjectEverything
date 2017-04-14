package com.example.hridoy.menu;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExternalStorage extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private TextView canWrite,canRead;
    private String state,f;
    private EditText saveAs;
    boolean canW,canR;
    private Spinner spinner;
    private Button bConfirm,bSave;
    String[] paths={"music","pictures","download"};
    File filePath=null;
    File actualFile= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_storage);
        canWrite=(TextView)findViewById(R.id.tvCanWrite);
        canRead=(TextView)findViewById(R.id.tvCanRead);
        bConfirm=(Button)findViewById(R.id.bConfirm);
        bSave=(Button)findViewById(R.id.bSaveFile);
        saveAs=(EditText)findViewById(R.id.etSaveAs);
        checkState();


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ExternalStorage.this,android.R.layout.simple_spinner_item,paths);
        spinner = (Spinner)findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        bConfirm.setOnClickListener(this);
        bSave.setOnClickListener(this);

        f=saveAs.getText().toString();
    }

    private void checkState() {
        state= Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            //Read & Write
            canWrite.setText("true");
            canRead.setText("true");
            canW=canR=true;
        }else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            //Read but can't Write
            canWrite.setText("false");
            canRead.setText("true");
            canR=true;
            canW=false;
        }else {
            canWrite.setText("false");
            canRead.setText("false");
            canW=canR=false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int pos=spinner.getSelectedItemPosition();
        switch (pos){
            case 0:
                filePath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);

                break;
            case 1:
                filePath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                break;
            case 2:
                filePath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bSaveFile:
                actualFile=new File(filePath,f);
                checkState();
                if(canW==canR==true){

                    filePath.mkdirs();

                    try {
                        InputStream is=getResources().openRawResource(R.raw.asad_720p);
                        OutputStream os=new FileOutputStream(actualFile);
                        byte[] data=new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        os.close();
                        Toast.makeText(ExternalStorage.this,"File Has Been Saved",Toast.LENGTH_LONG).show();
                        //Update files for user to use
                        MediaScannerConnection.scanFile(ExternalStorage.this,
                                new String[]{filePath.toString()},
                                null,
                                new MediaScannerConnection.OnScanCompletedListener() {
                                    @Override
                                    public void onScanCompleted(String s, Uri uri) {
                                        Toast.makeText(ExternalStorage.this, "Scan Complete", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.bConfirm:
                bSave.setVisibility(View.VISIBLE);
                break;
        }

    }
}
