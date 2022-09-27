package com.example.myintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText te;
    Button ex, lo;
    View.OnClickListener cl;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        te = (EditText) findViewById(R.id.text);
        ex = (Button) findViewById(R.id.execute);
        lo = (Button) findViewById(R.id.login);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.execute:
                        i = new Intent();
                        i.setAction(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(te.getText().toString()));
                        startActivity(i);
                        break;
                    case R.id.login:
                        i = new Intent(getApplicationContext(), login.class);
                        startActivity(i);
                        break;
                }
            }
        };
        ex.setOnClickListener(cl);
        lo.setOnClickListener(cl);
    }
}