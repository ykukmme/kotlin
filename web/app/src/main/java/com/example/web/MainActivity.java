package com.example.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    EditText te;
    Button go;
    WebView wv;
    ImageButton ba, fo, en;
    View.OnClickListener cl;
    View.OnKeyListener kl;
    MyClient mc;

    class MyClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        te = (EditText) findViewById(R.id.text);
        go = (Button) findViewById(R.id.go);
        wv = (WebView) findViewById(R.id.space);
        ba = (ImageButton) findViewById(R.id.back);
        fo = (ImageButton) findViewById(R.id.forw);
        en = (ImageButton) findViewById(R.id.enter);

        mc = new MyClient();
        wv.setWebViewClient(mc);

        WebSettings ui;
        ui = wv.getSettings();
        ui.setJavaScriptEnabled(true);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.go:
                        String s;
                        s = te.getText().toString();
                        if(s.substring(0,4).equals("http")==true)
                            wv.loadUrl(s);
                        else
                            wv.loadUrl("http://"+s);
                        break;
                    case R.id.back:
                        wv.goBack();
                        break;
                    case R.id.forw:
                        wv.goForward();
                        break;
                    case R.id.enter:
                        String a;
                        a = te.getText().toString();
                        if(a.substring(0,4).equals("http")==true)
                            wv.loadUrl(a);
                        else
                            wv.loadUrl("http://"+a);
                        break;
                }
            }
        };
        kl = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i==KeyEvent.KEYCODE_ENTER){
                    String s;
                    s = te.getText().toString();
                    if(s.substring(0,4).equals("http")==true)
                        wv.loadUrl(s);
                    else
                        wv.loadUrl("http://"+s);
                }
                return false;
            }
        };
        go.setOnClickListener(cl);
        ba.setOnClickListener(cl);
        fo.setOnClickListener(cl);
        en.setOnClickListener(cl);
        te.setOnKeyListener(kl);
    }
}