package com.example.myintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity {

    EditText id, pw;
    Button ch;
    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        id = (EditText) findViewById(R.id.myid);
        pw = (EditText) findViewById(R.id.mypw);

        ch = (Button) findViewById(R.id.check);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.check:
                        if(id.getText().toString().equals("guest")){
                            if(pw.getText().toString().equals("1234")){
                                Intent i = new Intent(getApplicationContext(), main.class);
                                i.putExtra("hid", "guest");
                                startActivity(i);
                                break;
                            } else {
                                Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_LONG).show();
                            }
                        } else{
                            Toast.makeText(getApplicationContext(), "ID가 없습니다", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
        };
        ch.setOnClickListener(cl);

    }
}
