package com.example.zoom;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    EditText te;
    float d1, d2, ratio;
    Matrix m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.image);
        te = (EditText) findViewById(R.id.text);
    }

    float getDistance(MotionEvent e) {
        float dx = e.getX(1) - e.getX(0);
        float dy = e.getY(1) - e.getY(0);
        return (float) Math.sqrt(dx*dx + dy*dy);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String s;
        int action = event.getAction() & 0xff;
        if(event.getPointerCount()==2){
            switch (action){
                case MotionEvent.ACTION_POINTER_DOWN:
                    d1 = getDistance(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_POINTER_UP:
                    d2 = getDistance(event);
                    ratio = d2/d1;
                    m = new Matrix();
                    m.postScale(ratio, ratio);
                    iv.setImageMatrix(m);
                    s = String.format("d1= %4f, d2= %4f, ratio= %4f", d1, d2, ratio);
                    te.setText(s);
                    break;
            }
        }
        return true; //super.onTouchEvent(event);
    }
}