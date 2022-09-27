package com.example.bitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyView mv;

    class MyView extends View {
        Paint p;
        float x1, y1, x2, y2;
        Bitmap bm;

        MyView(Context c){
            super(c);
            p = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            /*
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(5);
            canvas.drawRect(x1, y1, x2, y2, p);
            */
             bm = BitmapFactory.decodeResource(getResources(), R.drawable.sc);
             canvas.rotate(20);
             canvas.drawBitmap(bm, 0, 0, p);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    x1 = event.getX();
                    y1 = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    x2 = event.getX();
                    y2 = event.getY();
                    invalidate();
                    break;
            }
            return true; //super.onTouchEvent(event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv = new MyView(this);
        setContentView(mv);
    }
}