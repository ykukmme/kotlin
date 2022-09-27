package com.example.clipboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inp;
    TextView out;
    Button cp1, pa1, cp2, pa2;
    View.OnClickListener cl;
    ClipboardManager cm;
    ClipData data;
    ClipData.Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inp = (EditText) findViewById(R.id.input);
        out = (TextView) findViewById(R.id.output);
        cp1 = (Button) findViewById(R.id.copy1);
        pa1 = (Button) findViewById(R.id.paste1);
        cp2 = (Button) findViewById(R.id.copy2);
        pa2 = (Button) findViewById(R.id.paste2);

        cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.copy1:
                        data = ClipData.newPlainText("aaa", inp.getText().toString());
                        cm.setPrimaryClip(data);
                        Toast.makeText(getApplicationContext(), "copy done!", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.copy2:
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(inp.getText().toString()));
                        data = ClipData.newIntent("bbb", i);
                        cm.setPrimaryClip(data);
                        Toast.makeText(getApplicationContext(), "copy done!", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.paste1:
                        if(cm.hasPrimaryClip() == false){
                            Toast.makeText(getApplicationContext(), "클립보드가 비어있습니다!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(cm.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) == false){
                            Toast.makeText(getApplicationContext(), "type mismatch!(not text)", Toast.LENGTH_LONG).show();
                            return;
                        }
                        data = cm.getPrimaryClip();
                        item = data.getItemAt(0);
                        out.setText(item.getText());
                        break;
                    case R.id.paste2:
                        if(cm.hasPrimaryClip() == false){
                            Toast.makeText(getApplicationContext(), "클립보드가 비어있습니다!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(cm.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_INTENT) == false){
                            Toast.makeText(getApplicationContext(), "type mismatch!(not text)", Toast.LENGTH_LONG).show();
                            return;
                        }
                        data = cm.getPrimaryClip();
                        item = data.getItemAt(0);
                        //out.setText(item.getText());
                        Intent ii = item.getIntent();
                        startActivity(ii);
                        break;
                }
            }
        };
        cp1.setOnClickListener(cl);
        pa1.setOnClickListener(cl);
        cp2.setOnClickListener(cl);
        pa2.setOnClickListener(cl);
    }
}