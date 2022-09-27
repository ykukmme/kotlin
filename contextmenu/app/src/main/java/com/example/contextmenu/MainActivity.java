package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText et;
    Button re;

    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = (TextView) findViewById(R.id.text);
        et = (EditText) findViewById(R.id.edit);

        registerForContextMenu(tv);
        registerForContextMenu(et);

        re = (Button) findViewById(R.id.red);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setBackgroundColor(Color.RED);
            }
        };
        re.setOnClickListener(cl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 101, 0, "파란색");
        menu.add(0, 102, 0, "초록색");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 101:
                et.setBackgroundColor(Color.BLUE);
                break;
            case 102:
                et.setBackgroundColor(Color.GREEN);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v==tv){
            menu.add(0, 201, 0, "노란색");
            menu.add(0, 202, 0, "하늘색");
        } else if(v==et){
            menu.add(0, 301, 0, "안녕");
            menu.add(0, 302, 0, "이름");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 201:
                tv.setBackgroundColor(Color.YELLOW);
                break;
            case 202:
                tv.setBackgroundColor(Color.CYAN);
                break;
            case 301:
                et.setText("안녕하세요");
                break;
            case 302:
                et.setText("나는 김경민입니다.");
                break;
        }
        return super.onContextItemSelected(item);
    }
}