package com.example.pinch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (EditText) findViewById(R.id.text);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        String s;
        int index;
        int action;
        action = event.getAction() & 0xff;
        index = event.getAction() >> 8;

        switch (action){
            case MotionEvent.ACTION_DOWN:
                s = String.format("down x= %4.0f y= %4.0f, index= %3d", event.getX(index), event.getY(index), index);
                txt.setText(s);
                break;
            case MotionEvent.ACTION_MOVE:
                s = String.format("move x= %4.0f y= %4.0f, index= %3d", event.getX(index), event.getY(index), index);
                txt.setText(s);
                break;
            case MotionEvent.ACTION_UP:
                s = String.format("up x= %4.0f y= %4.0f, index= %3d", event.getX(index), event.getY(index), index);
                txt.setText(s);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                s = String.format("pointer up x= %4.0f y= %4.0f, index= %3d", event.getX(index), event.getY(index), index);
                txt.setText(s);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                s = String.format("pointer down x= %4.0f y= %4.0f, index= %3d", event.getX(index), event.getY(index), index);
                txt.setText(s);
                break;
        }

        return super.onTouchEvent(event);
    }
}