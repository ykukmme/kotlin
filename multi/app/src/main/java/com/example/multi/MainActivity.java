package com.example.multi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b;
    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button3);
        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.setText(R.string.myname);
            }
        };
        b.setOnClickListener(cl);
    }
}