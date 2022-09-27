package com.example.myintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class main extends Activity {

    Intent i;
    EditText te;
    Button tv, re, wa;
    View.OnClickListener cl;
    int mytotal = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        te = (EditText) findViewById(R.id.text1);
        tv = (Button) findViewById(R.id.tv);
        re = (Button) findViewById(R.id.ref);
        wa = (Button) findViewById(R.id.wash);

        i = getIntent();
        String yid = i.getStringExtra("hid");
        te.setText(yid+"님 "+te.getText().toString());

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.tv:
                        i = new Intent(getApplicationContext(), tv.class);
                        i.putExtra("mytotal", mytotal);
                        startActivityForResult(i, 10);
                        break;
                    case R.id.ref:
                        i = new Intent(getApplicationContext(), ref.class);
                        i.putExtra("mytotal", mytotal);
                        startActivityForResult(i, 20);
                        break;
                    case R.id.wash:
                        i = new Intent(getApplicationContext(), wash.class);
                        i.putExtra("mytotal", mytotal);
                        startActivityForResult(i, 30);
                        break;
                }
            }
        };
        tv.setOnClickListener(cl);
        re.setOnClickListener(cl);
        wa.setOnClickListener(cl);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10){
            if (resultCode == 100){
                mytotal = mytotal + data.getIntExtra("tvresult", 0);
            }
        } else if(requestCode==20){
            if (resultCode == 200){
                mytotal = mytotal + data.getIntExtra("refresult", 0);
            }
        } else if(requestCode==30){
            if (resultCode == 300){
                mytotal = mytotal + data.getIntExtra("washresult", 0);
            }
        }
    }
}
