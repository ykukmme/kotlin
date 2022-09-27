package com.example.aview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] general = {"이순신", "강감찬", "김유신", "을지문덕", "권율"};
    ArrayList<String> color;
    ListView lv;
    ArrayAdapter<String> adapter;
    AdapterView.OnItemClickListener icl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color = new ArrayList<String>();
        color.add("빨강색");
        color.add("파란색");
        color.add("노랑색");
        color.add("하늘색");
        color.remove(2);

        lv = (ListView) findViewById(R.id.list);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, general);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, color);

        lv.setAdapter(adapter);

        icl = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "번호= " + i + " 내용= " + color.get(i), Toast.LENGTH_LONG).show();
            }
        };
        lv.setOnItemClickListener(icl);
    }
}