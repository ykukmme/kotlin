package com.example.myintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class wash extends Activity {

    EditText te;
    Button ss, lg, wi, fi3;
    View.OnClickListener cl;
    int total=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wash);

        te = (EditText) findViewById(R.id.text4);
        ss = (Button) findViewById(R.id.sswash);
        lg = (Button) findViewById(R.id.lgwash);
        wi = (Button) findViewById(R.id.wiwash);

        Intent i = getIntent();
        total = i.getIntExtra("mytotal", 0);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.sswash:
                        total = total +180;
                        te.setText("금액= " +total);
                        break;
                    case R.id.lgwash:
                        total = total +230;
                        te.setText("금액= " +total);
                        break;
                    case R.id.wiwash:
                        total = total +170;
                        te.setText("금액= " +total);
                        break;
                    case R.id.finish3:
                        Intent j = new Intent();
                        j.putExtra("refresult", total);
                        setResult(300, j);
                        finish();
                }
            }
        };
        ss.setOnClickListener(cl);
        lg.setOnClickListener(cl);
        wi.setOnClickListener(cl);
        fi3.setOnClickListener(cl);
    }
}
