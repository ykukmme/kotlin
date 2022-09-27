package com.example.myintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class ref extends Activity {

    EditText te;
    Button ss, lg, wi, fi2;
    View.OnClickListener cl;
    int total=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ref);

        te = (EditText) findViewById(R.id.text3);
        ss = (Button) findViewById(R.id.ssref);
        lg = (Button) findViewById(R.id.lgref);
        wi = (Button) findViewById(R.id.wiref);
        fi2 = (Button) findViewById(R.id.finish2);

        Intent i = getIntent();
        total = i.getIntExtra("mytotal", 0);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ssref:
                        total = total +280;
                        te.setText("금액= " +total);
                        break;
                    case R.id.lgref:
                        total = total +320;
                        te.setText("금액= " +total);
                        break;
                    case R.id.wiref:
                        total = total +220;
                        te.setText("금액= " +total);
                        break;
                    case R.id.finish2:
                        Intent j = new Intent();
                        j.putExtra("refresult", total);
                        setResult(200, j);
                        finish();
                }
            }
        };
        ss.setOnClickListener(cl);
        lg.setOnClickListener(cl);
        wi.setOnClickListener(cl);
        fi2.setOnClickListener(cl);
    }
}
