package com.example.filedown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText te;
    TextView tv;
    Button go, down;
    View.OnClickListener cl;
    WebView sc;
    String page = "";
    MyHandler mh;

    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==20){
                tv.setText(page);
            }
        }
    }

    class DownTread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                URL u = new URL(te.getText().toString());
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                if(conn != null){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    page = "";
                    String line = null;
                    while ((line=reader.readLine()) != null) {
                        page = page + line + "\n";
                    }
                    //tv.setText(page);
                    mh.sendEmptyMessage(20);
                    reader.close();
                    conn.disconnect();
                }
            } catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

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

        mh = new MyHandler();

        te = (EditText) findViewById(R.id.text);
        tv = (TextView) findViewById(R.id.contents);

        go = (Button) findViewById(R.id.go);
        down = (Button) findViewById(R.id.down);

        sc = (WebView) findViewById(R.id.screen);

        MyClient mc = new MyClient();
        sc.setWebViewClient(mc);
        WebSettings ws = sc.getSettings();
        ws.setJavaScriptEnabled(true);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.go:
                        sc.loadUrl(te.getText().toString());
                        break;
                    case R.id.down:
                        DownTread dt;
                        dt = new DownTread();
                        dt.start();
                        break;
                }
            }
        };
        go.setOnClickListener(cl);
        down.setOnClickListener(cl);
    }
}