package com.example.thread1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText te1, te2, te3, te4;
    Button sta1, sto1, sta2, sto2, sta3, sto3, sta4, sto4;
    View.OnClickListener cl;
    int g1=0, g2=0, g3=0, g4=0;
    boolean b1=true, b2=true, b3=true, b4=true;
    MyHandler mh;

    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                te1.setText("g1= "+g1);
            } else if (msg.what==2){
                te2.setText("g2= "+g2);
            } else if (msg.what==3){
                te3.setText("g3= "+g3);
            } else if (msg.what==10){
                if (b4==true){
                    g4++;
                    te4.setText("g4= "+g4);
                    mh.sendEmptyMessageDelayed(10, 1000);
                }
            }
        }
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (b1==true) {
                g1++;
                SystemClock.sleep(500);
                //te1.setText("g1= "+g1);
                mh.sendEmptyMessage(1);
            }
        }
    }

    class YourThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (b2==true){
                g2++;
                SystemClock.sleep(300);
                //te1.setText("g2= "+g2);
                mh.sendEmptyMessage(2);
            }
        }
    }

    class ThirdTread extends Thread{
        @Override
        public void run() {
            super.run();
            while (b3==true){
                g3++;
                SystemClock.sleep(200);
                //te1.setText("g3= "+g3);
                mh.sendEmptyMessage(3);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mh = new MyHandler();

        te1 = (EditText) findViewById(R.id.text1);
        te2 = (EditText) findViewById(R.id.text2);
        te3 = (EditText) findViewById(R.id.text3);
        te4 = (EditText) findViewById(R.id.text4);

        sta1 = (Button) findViewById(R.id.start1);
        sta2 = (Button) findViewById(R.id.start2);
        sta3 = (Button) findViewById(R.id.start3);
        sta4 = (Button) findViewById(R.id.start4);

        sto1 = (Button) findViewById(R.id.stop1);
        sto2 = (Button) findViewById(R.id.stop2);
        sto3 = (Button) findViewById(R.id.stop3);
        sto4 = (Button) findViewById(R.id.stop4);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.start1:
                        MyThread mt;
                        mt = new MyThread();
                        b1 = true;
                        g1=0;
                        mt.start();
                        break;
                    case R.id.stop1:
                        b1 = false;
                        break;
                    case R.id.start2:
                        YourThread yt;
                        yt = new YourThread();
                        b2 = true;
                        g2=0;
                        yt.start();
                        break;
                    case R.id.stop2:
                        b2 = false;
                        break;
                    case R.id.start3:
                        ThirdTread tt;
                        tt = new ThirdTread();
                        b3 = true;
                        g3=0;
                        tt.start();
                        break;
                    case R.id.stop3:
                        b3 = false;
                        break;
                    case R.id.start4:
                        b4=true;
                        g4=0;
                        mh.sendEmptyMessage(10);
                        break;
                    case R.id.stop4:
                        b4 = false;
                        break;

                }
            }
        };
        sta1.setOnClickListener(cl);
        sto1.setOnClickListener(cl);
        sta2.setOnClickListener(cl);
        sto2.setOnClickListener(cl);
        sta3.setOnClickListener(cl);
        sto3.setOnClickListener(cl);
        sta4.setOnClickListener(cl);
        sto4.setOnClickListener(cl);
    }
}