package com.example.hridoy.menu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.net.ConnectException;

/**
 * Created by Hridoy on 12-07-16.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {

    WebView ourBrowser;
    EditText url;
    Button bBack,bNext,bRefresh,bClearHistory,bGo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_browser);
        initi();
    }

    private void initi() {
        ourBrowser = (WebView)findViewById(R.id.webViewBrowser);
        ourBrowserSettings();
        ourBrowser.setWebViewClient(new ourWebViewClient());

        try{
            ourBrowser.loadUrl("http://www.mybringback.com");
        }catch (RuntimeException e){
            e.printStackTrace();
        }

        url=(EditText)findViewById(R.id.etURL);

        bBack=(Button)findViewById(R.id.bBack);
        bNext=(Button)findViewById(R.id.bNext);
        bRefresh=(Button)findViewById(R.id.bRefresh);
        bClearHistory=(Button)findViewById(R.id.bClearHistory);
        bGo=(Button)findViewById(R.id.bGo);

        bBack.setOnClickListener(this);
        bNext.setOnClickListener(this);
        bRefresh.setOnClickListener(this);
        bClearHistory.setOnClickListener(this);
        bGo.setOnClickListener(this);
    }

    private void ourBrowserSettings() {

        ourBrowser.getSettings().setJavaScriptEnabled(true);
        ourBrowser.getSettings().setLoadWithOverviewMode(true);
        ourBrowser.getSettings().setUseWideViewPort(true);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bGo:

                //...................Hinding KeyBoard............
                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromInputMethod(url.getWindowToken(),0);

                String theUrl;
                theUrl=url.getText().toString();
                ourBrowser.loadUrl(theUrl);

                break;
            case R.id.bBack:
                if(ourBrowser.canGoBack())
                    ourBrowser.goBack();
                break;
            case R.id.bNext:
                if(ourBrowser.canGoForward())
                    ourBrowser.goBack();
                break;
            case R.id.bRefresh:
                ourBrowser.reload();
                break;
            case R.id.bClearHistory:
                ourBrowser.clearHistory();
                break;
        }

    }
}
