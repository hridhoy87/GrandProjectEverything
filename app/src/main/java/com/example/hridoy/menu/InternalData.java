package com.example.hridoy.menu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Hridoy on 12-07-16.
 */
public class InternalData extends AppCompatActivity implements View.OnClickListener {

    //These are three views
    EditText sharedData;
    Button bSave,bLoad;
    TextView dataResult;
    //
    FileOutputStream fos;
    //Just a String pore define kora ase
    String fileName;
    //I dont know why android.content.SharedPreferences why noy just SharedPreferences
    android.content.SharedPreferences sharedString;
    //the File name which we will creat (may be)
    public static String myFileName="My Shared File";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences);
        initialize();
    }

    private void initialize() {
        //Initializing 3 Views et,bu,tv;
        sharedData=(EditText)findViewById(R.id.et);
        bSave=(Button)findViewById(R.id.bSave);
        bLoad=(Button)findViewById(R.id.bLoad);
        dataResult=(TextView) findViewById(R.id.tvLoadedResult);
        //What is this??
        fileName="InternalString";

        openAndCloseFileOfFileNameInternalString();

        sharedString=getSharedPreferences(myFileName,0);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //EditText a ja likha hobe ota save kora hobe save chaple
                String data=sharedData.getText().toString();

                //Saving Data via FILE
                /* File f=new File(fileName);
                try {
                    fos=new FileOutputStream(f);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                try {
                    fos=openFileOutput(fileName,Context.MODE_APPEND);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        bLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //evabe load korle main thread a onk chap pore tai arekta class toiri kore Asyntask a korano hoy
                /*String collected = null;
                FileInputStream fis=null;
                try {
                    fis =openFileInput(fileName);
                    byte[] dataArray= new byte[fis.available()];
                    while(fis.read(dataArray)!=-1){
                        collected = new String(dataArray);

                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fis.close();
                        dataResult.setText(collected);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/


                /*
                *  2nd Method where a class will be created which will extend Asynctask
                *  and do our work of LOADING in a different thread;
                *  The line below is just creating an object of that class to call it
                *  :)
                */

                new LoadSomeStaffs().execute(fileName);
            }
        });
    }

    private void openAndCloseFileOfFileNameInternalString() {
        try{
            //FileOutputStream fileName nam er file khulbe, context mode private
            fos= openFileOutput(fileName, Context.MODE_PRIVATE);
            //Open korle Close korte hoy
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

    }

    //*******************************External Class Create করে পারি না********************************

    public class LoadSomeStaffs extends AsyncTask<String,Integer,String> {

        //Asynctask er 4ta class ase PreExecute,doInBackground,ProgressUpdate,PostExecute

        ProgressDialog dialogue;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialogue = new ProgressDialog(InternalData.this);

            dialogue.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialogue.setMax(100);
            dialogue.show();


        }


        /*
        protected void onPreExecute(String f){
            //Example of setting up somethig
            f="whatever";

        }
        */


        @Override          //3dots means Array Here String [] strings; eta bujhano hoyese
        protected String doInBackground(String... strings) {

            String collected = null;
            FileInputStream fis=null;

            //String fileName="InternalString";
            for(int i=0; i<20;i++){
                publishProgress(5);
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dialogue.dismiss();
            try {
                byte[] dataArray= new byte[fis.available()/*Length Dewa holo*/];
                fis =openFileInput(fileName);
                //This means read korbe untill shob read na kore
                while(fis.read(dataArray)!=-1){
                    collected = new String(dataArray);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RuntimeException e){
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    //dataResult.setText(collected);
                    fis.close();
                    //dataResult.setText(collected);
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }



        /*protected void onProgressUpdate(Integer...progress){

        }*/

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            dialogue.incrementProgressBy(5);
        }
        /*protected void onPostExecute(String result){
            dataResult.setText(result);
        }*/

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
