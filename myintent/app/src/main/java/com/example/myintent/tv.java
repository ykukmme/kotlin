package com.example.myintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class tv extends Activity {

    EditText te;
    Button s55, l75, s65, fi1;
    View.OnClickListener cl;

    int total=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv);

        te = (EditText) findViewById(R.id.text2);
        s55 = (Button) findViewById(R.id.sstv55);
        l75 = (Button) findViewById(R.id.lgtv75);
        s65 = (Button) findViewById(R.id.sstv65);
        fi1 = (Button) findViewById(R.id.finish1);

        Intent i = getIntent();
        total = i.getIntExtra("mytotal", 0);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.sstv55:
                        total = total + 120;
                        te.setText("금액= "+total);
                        break;
                    case R.id.lgtv75:
                        total = total + 250;
                        te.setText("금액= "+total);
                        break;
                    case R.id.sstv65:
                        total = total + 180;
                        te.setText("금액= "+total);
                        break;
                    case R.id.finish1:
                        Intent j = new Intent();
                        j.putExtra("tvresult", total);
                        setResult(100, j);
                        finish();
                }
            }
        };
        s55.setOnClickListener(cl);
        l75.setOnClickListener(cl);
        s65.setOnClickListener(cl);
        fi1.setOnClickListener(cl);
    }
}
